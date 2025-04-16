package org.onedroid.radiowave.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.EmptyResult
import org.onedroid.radiowave.app.utils.Result
import org.onedroid.radiowave.app.utils.map
import org.onedroid.radiowave.data.database.RadioWaveDao
import org.onedroid.radiowave.data.mappers.toRadio
import org.onedroid.radiowave.data.mappers.toRadioEntity
import org.onedroid.radiowave.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.domain.Radio
import org.onedroid.radiowave.domain.RadioRepository

class RadioRepositoryImpl(
    private val remoteRadioDataSource: RemoteRadioDataSource,
    private val radioWaveDao: RadioWaveDao
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

    override suspend fun saveRadio(radio: Radio): EmptyResult<DataError.Local> {
        return try {
            radioWaveDao.upsert(
                radio.toRadioEntity(
                    isSaved = true
                )
            )
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }


    override suspend fun deleteFromSaved(id: String) {
        radioWaveDao.deleteSavedRadio(id)
    }

    override fun getSavedRadios(): Flow<List<Radio>> {
        return radioWaveDao.getSavedRadios().map { radioEntities ->
            radioEntities.sortedByDescending { it.timeStamp }.map { it.toRadio() }
        }
    }

    override suspend fun isSaved(id: String): Flow<Boolean> {
        return radioWaveDao.isSaved(id)
    }

    override suspend fun insertRecentlyUpdatedRadios(radios: List<Radio>): EmptyResult<DataError.Local> {
        return try {
            radioWaveDao.upsertRecentlyUpdatedRadios(radios.map { it.toRadioEntity() })
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getRecentlyUpdatedRadios(): Flow<List<Radio>> {
        return radioWaveDao.getAllRadios().map { radioEntities ->
            radioEntities.map { it.toRadio() }
        }
    }
}