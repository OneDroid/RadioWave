package org.onedroid.radiowave.domain

import org.onedroid.radiowave.app.utils.DataError
import org.onedroid.radiowave.app.utils.Result

interface RadioRepository {
    suspend fun searchRadios(query: String): Result<List<Radio>, DataError.Remote>
    suspend fun getRadios(page: Int): Result<List<Radio>, DataError.Remote>
}