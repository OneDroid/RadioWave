package org.onedroid.radiowave.data.network

import org.onedroid.radiowave.data.dto.RadioSearchResponseDto
import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.Result

interface RemoteRadioDataSource {
    suspend fun searchRadio(
        query: String,
        resultLimit: Int? = 50
    ): Result<List<RadioSearchResponseDto>, DataError.Remote>

    suspend fun fetchRadios(
        resultLimit: Int? = 99,
        page: Int
    ): Result<List<RadioSearchResponseDto>, DataError.Remote>
}