package org.onedroid.radiowave.domain

import kotlinx.coroutines.flow.Flow
import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.EmptyResult
import org.onedroid.radiowave.app.utils.Result

interface RadioRepository {
    suspend fun searchRadios(query: String): Result<List<Radio>, DataError.Remote>
    suspend fun getRadios(offset: Int, limit: Int): Result<List<Radio>, DataError.Remote>

    suspend fun saveRadio(radio: Radio): EmptyResult<DataError.Local>
    suspend fun deleteFromSaved(id: String)
    fun getSavedRadios(): Flow<List<Radio>>

    suspend fun isSaved(id: String): Flow<Boolean>

    suspend fun insertRecentlyUpdatedRadios(radios: List<Radio>): EmptyResult<DataError.Local>
    fun getRecentlyUpdatedRadios(): Flow<List<Radio>>
}