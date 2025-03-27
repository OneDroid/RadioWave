package org.onedroid.radiowave.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RadioDto(
    @SerialName("bitrate")
    val bitrate: Int,
    @SerialName("changeuuid")
    val changeuuid: String,
    @SerialName("clickcount")
    val clickcount: Int,
    @SerialName("clicktimestamp")
    val clicktimestamp: String,
    @SerialName("clicktimestamp_iso8601")
    val clicktimestampIso8601: String?,
    @SerialName("clicktrend")
    val clicktrend: Int,
    @SerialName("codec")
    val codec: String,
    @SerialName("country")
    val country: String,
    @SerialName("countrycode")
    val countrycode: String,
    @SerialName("favicon")
    val favicon: String,
    @SerialName("geo_lat")
    val geoLat: Double?,
    @SerialName("geo_long")
    val geoLong: Double?,
    @SerialName("has_extended_info")
    val hasExtendedInfo: Boolean,
    @SerialName("hls")
    val hls: Int,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("iso_3166_2")
    val iso31662: String?,
    @SerialName("language")
    val language: String,
    @SerialName("languagecodes")
    val languagecodes: String,
    @SerialName("lastchangetime")
    val lastchangetime: String,
    @SerialName("lastchangetime_iso8601")
    val lastchangetimeIso8601: String,
    @SerialName("lastcheckok")
    val lastcheckok: Int,
    @SerialName("lastcheckoktime")
    val lastcheckoktime: String,
    @SerialName("lastcheckoktime_iso8601")
    val lastcheckoktimeIso8601: String,
    @SerialName("lastchecktime")
    val lastchecktime: String,
    @SerialName("lastchecktime_iso8601")
    val lastchecktimeIso8601: String,
    @SerialName("lastlocalchecktime")
    val lastlocalchecktime: String,
    @SerialName("lastlocalchecktime_iso8601")
    val lastlocalchecktimeIso8601: String,
    @SerialName("name")
    val name: String,
    @SerialName("serveruuid")
    val serveruuid: String?,
    @SerialName("ssl_error")
    val sslError: Int,
    @SerialName("state")
    val state: String,
    @SerialName("stationuuid")
    val stationuuid: String,
    @SerialName("tags")
    val tags: String,
    @SerialName("url")
    val url: String,
    @SerialName("url_resolved")
    val urlResolved: String,
    @SerialName("votes")
    val votes: Int
)