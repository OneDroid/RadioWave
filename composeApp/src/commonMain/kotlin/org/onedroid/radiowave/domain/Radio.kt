package org.onedroid.radiowave.domain

data class Radio(
    val id: String,
    val name: String,
    val url: String,
    val urlResolved: String?,
    val homepage: String?,
    val imgUrl: String?,
    val tags : List<String>?,
    val country: String?,
    val state: String?,
    val iso: String?,
    val language: List<String>?,
    val codec: String?,
    val bitrate: Int?,
    val hls: Int?,
    val voteCount: Int?,
    val clickCount: Int?,
    val sslError: Int?,
    val geoLat: Double?,
    val geoLong: Double?,
    val hasExtendedInfo: Boolean?,
    val lastPlayed: Long? = null
)