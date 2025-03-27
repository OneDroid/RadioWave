package org.onedroid.radiowave.app.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import org.onedroid.radiowave.app.data.dto.RadioSearchResponseDto
import org.onedroid.radiowave.core.utils.DataError
import org.onedroid.radiowave.core.utils.RADIO_BROWSER_BASE_URL
import org.onedroid.radiowave.core.utils.Result
import org.onedroid.radiowave.core.utils.USER_AGENT
import org.onedroid.radiowave.core.utils.safeCall

class RemoteRadioDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteRadioDataSource {
    override suspend fun searchRadio(
        query: String,
        resultLimit: Int?
    ): Result<List<RadioSearchResponseDto>, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "${RADIO_BROWSER_BASE_URL}/stations/search"
            ) {
                header("User-Agent", USER_AGENT)
                parameter("name", query)
                parameter("limit", resultLimit)
                parameter("hidebroken", "true")
                parameter("order", "clickcount")
            }
        }
    }

    override suspend fun fetchRadios(
        resultLimit: Int?,
        page: Int
    ): Result<List<RadioSearchResponseDto>, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "${RADIO_BROWSER_BASE_URL}/stations"
            ) {
                header("User-Agent", USER_AGENT)
                parameter("limit", resultLimit)
                parameter("hidebroken", "true")
                parameter("order", "clickcount")
                parameter("reverse", "true")
                parameter("has_extended_info", "true")
            }
        }
    }
}