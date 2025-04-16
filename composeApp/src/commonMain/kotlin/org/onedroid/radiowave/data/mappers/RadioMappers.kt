package org.onedroid.radiowave.data.mappers

import kotlinx.datetime.Clock
import org.onedroid.radiowave.data.database.RadioEntity
import org.onedroid.radiowave.data.dto.RadioSearchResponseDto
import org.onedroid.radiowave.domain.Radio

fun RadioSearchResponseDto.toRadio(): Radio {
    return Radio(
        id = id,
        name = name,
        url = url,
        urlResolved = urlResolved,
        homepage = homepage,
        imgUrl = imgUrl,
        tags = tags?.split(",")?.map { it.trim() },
        country = country,
        state = state,
        iso = iso,
        language = language?.split(",")?.map { it.trim() },
        codec = codec,
        bitrate = bitrate,
        hls = hls,
        voteCount = voteCount,
        clickCount = clickCount,
        sslError = sslError,
        geoLat = geoLat,
        geoLong = geoLong,
        hasExtendedInfo = hasExtendedInfo,
    )
}

fun RadioEntity.toRadio(): Radio {
    return Radio(
        id = stationuuid,
        name = name,
        url = url,
        urlResolved = urlResolved,
        homepage = homepage,
        imgUrl = favicon,
        tags = tags.split(",").map { it.trim() },
        country = country,
        state = state,
        iso = countrycode,
        language = language.split(",").map { it.trim() },
        codec = "",
        bitrate = bitrate,
        hls = hls,
        voteCount = clickcount,
        clickCount = clicktrend,
        sslError = 0,
        geoLat = geoLat,
        geoLong = geoLong,
        hasExtendedInfo = hasExtendedInfo,
    )
}

fun Radio.toRadioEntity(
    isSaved: Boolean = false
): RadioEntity {
    return RadioEntity(
        stationuuid = id,
        name = name,
        url = url,
        urlResolved = urlResolved ?: "None",
        homepage = homepage ?: "None",
        favicon = imgUrl ?: "None",
        tags = tags?.joinToString(", ") ?: "",
        country = country ?: "None",
        state = state ?: "None",
        countrycode = iso ?: "None",
        language = language?.joinToString(", ") ?: "",
        bitrate = bitrate ?: 0,
        hls = hls ?: 0,
        clickcount = voteCount ?: 0,
        clicktrend = clickCount ?: 0,
        geoLat = geoLat,
        geoLong = geoLong,
        hasExtendedInfo = hasExtendedInfo ?: false,
        isSaved = isSaved,
        timeStamp = Clock.System.now().toEpochMilliseconds(),
        serveruuid = "None"
    )
}