package org.onedroid.radiowave.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.RADIO_BROWSER_BASE_URL_SERVER1
import org.onedroid.radiowave.app.utils.RADIO_BROWSER_BASE_URL_SERVER2
import org.onedroid.radiowave.app.utils.RADIO_BROWSER_BASE_URL_SERVER3
import org.onedroid.radiowave.app.utils.Result
import org.onedroid.radiowave.app.utils.USER_AGENT
import org.onedroid.radiowave.app.utils.safeCall
import org.onedroid.radiowave.data.dto.RadioSearchResponseDto

class RemoteRadioDataSourceImpl(
    private val httpClient: HttpClient,
) : RemoteRadioDataSource {

    private val radioBrowserBaseUrls = listOf(
        RADIO_BROWSER_BASE_URL_SERVER1,
        RADIO_BROWSER_BASE_URL_SERVER2,
        RADIO_BROWSER_BASE_URL_SERVER3,
    )

    override suspend fun searchRadio(
        query: String,
        resultLimit: Int?
    ): Result<List<RadioSearchResponseDto>, DataError.Remote> {
        return fetchFromMirrors { baseUrl ->
            safeCall<List<RadioSearchResponseDto>> {
                httpClient.get("$baseUrl/stations/search") {
                    header("User-Agent", USER_AGENT)
                    parameter("name", query)
                    parameter("limit", resultLimit)
                    parameter("hidebroken", "true")
                    parameter("order", "clickcount")
                }
            }
        }
    }

    override suspend fun fetchRadios(
        offset: Int,
        limit: Int
    ): Result<List<RadioSearchResponseDto>, DataError.Remote> {
        return fetchFromMirrors { baseUrl ->
            safeCall<List<RadioSearchResponseDto>> {
                httpClient.get("$baseUrl/stations") {
                    header("User-Agent", USER_AGENT)
                    parameter("limit", limit)
                    parameter("offset", offset)
                    parameter("hidebroken", "true")
                    parameter("order", "clickcount")
                    parameter("reverse", "true")
                }
            }
        }
    }

    private suspend fun <T> fetchFromMirrors(
        attempt: suspend (String) -> Result<T, DataError.Remote>
    ): Result<T, DataError.Remote> {
        val errors = mutableListOf<DataError.Remote>()
        for (baseUrl in radioBrowserBaseUrls) {
            when (val result = attempt(baseUrl)) {
                is Result.Success -> return result
                is Result.Error -> errors.add(result.error)
            }
        }

        // Return the last error or a generic fallback
        return Result.Error(errors.lastOrNull() ?: DataError.Remote.UNKNOWN)
    }
}