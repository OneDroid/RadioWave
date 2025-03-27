package org.onedroid.radiowave.data.repository

import org.onedroid.radiowave.data.mappers.toRadio
import org.onedroid.radiowave.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.domain.Radio
import org.onedroid.radiowave.domain.RadioRepository
import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.Result
import org.onedroid.radiowave.app.utils.map

class RadioRepositoryImpl(
    private val remoteRadioDataSource: RemoteRadioDataSource,
) : RadioRepository {
    override suspend fun searchRadios(query: String): Result<List<Radio>, DataError.Remote> {
        return remoteRadioDataSource.searchRadio(query).map { responseDtoList ->
            responseDtoList.map { it.toRadio() }
        }
    }

    override suspend fun getRadios(page: Int): Result<List<Radio>, DataError.Remote> {
        return remoteRadioDataSource.fetchRadios(page = page).map { responseDtoList ->
            responseDtoList.map { it.toRadio() }
        }
    }
}