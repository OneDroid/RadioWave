package org.onedroid.radiowave.app.data.repository

import org.onedroid.radiowave.app.data.mappers.toRadio
import org.onedroid.radiowave.app.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.app.domain.Radio
import org.onedroid.radiowave.app.domain.RadioRepository
import org.onedroid.radiowave.core.utils.DataError
import org.onedroid.radiowave.core.utils.Result
import org.onedroid.radiowave.core.utils.map

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