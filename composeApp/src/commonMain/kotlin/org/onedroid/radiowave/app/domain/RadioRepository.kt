package org.onedroid.radiowave.app.domain

import org.onedroid.radiowave.core.utils.DataError
import org.onedroid.radiowave.core.utils.Result

interface RadioRepository {
    suspend fun searchRadios(query: String): Result<List<Radio>, DataError.Remote>
    suspend fun getRadios(page: Int): Result<List<Radio>, DataError.Remote>
}