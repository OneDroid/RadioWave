package org.onedroid.radiowave.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RadioSearchResponseDto(
    @SerialName("stationuuid") val id: String,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("url_resolved") val urlResolved: String?,
    @SerialName("homepage") val homepage: String?,
    @SerialName("favicon") val imgUrl: String?,
    @SerialName("tags") val tags: String?,
    @SerialName("country") val country: String?,
    @SerialName("state") val state: String?,
    @SerialName("iso_3166_2") val iso: String?,
    @SerialName("language") val language: String?,
    @SerialName("codec") val codec: String?,
    @SerialName("bitrate") val bitrate: Int?,
    @SerialName("hls") val hls: Int?,
    @SerialName("votes") val voteCount: Int?,
    @SerialName("clickcount") val clickCount: Int?,
    @SerialName("ssl_error") val sslError: Int?,
    @SerialName("geo_lat") val geoLat: Double?,
    @SerialName("geo_long") val geoLong: Double?,
    @SerialName("has_extended_info") val hasExtendedInfo: Boolean?
)