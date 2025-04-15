package org.onedroid.radiowave.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RadioEntity(
    @PrimaryKey(autoGenerate = false) val stationuuid: String,
    val bitrate: Int,
    val clickcount: Int,
    val clicktrend: Int,
    val country: String,
    val countrycode: String,
    val favicon: String,
    val geoLat: Double?,
    val geoLong: Double?,
    val hasExtendedInfo: Boolean,
    val hls: Int,
    val homepage: String,
    val language: String,
    val name: String,
    val serveruuid: String?,
    val state: String,
    val tags: String,
    val url: String,
    val urlResolved: String,
    val isSaved: Boolean,
    val timeStamp: Long?
)