package org.onedroid.radiowave.data.repository

import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.Result
import org.onedroid.radiowave.app.utils.map
import org.onedroid.radiowave.data.mappers.toRadio
import org.onedroid.radiowave.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.domain.Radio
import org.onedroid.radiowave.domain.RadioRepository

class RadioRepositoryImpl(
    private val remoteRadioDataSource: RemoteRadioDataSource,
) : RadioRepository {
    override suspend fun searchRadios(query: String): Result<List<Radio>, DataError.Remote> {
        return remoteRadioDataSource.searchRadio(query).map { responseDtoList ->
            responseDtoList.map { it.toRadio() }
        }
    }

    override suspend fun getRadios(
        offset: Int,
        limit: Int
    ): Result<List<Radio>, DataError.Remote> {
        return remoteRadioDataSource.fetchRadios(
            offset = offset,
            limit = limit
        ).map { responseDtoList ->
            responseDtoList.map { it.toRadio() }
        }
    }
}