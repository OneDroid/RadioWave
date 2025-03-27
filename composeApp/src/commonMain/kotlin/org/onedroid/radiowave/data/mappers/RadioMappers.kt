package org.onedroid.radiowave.data.mappers

import org.onedroid.radiowave.data.dto.RadioSearchResponseDto
import org.onedroid.radiowave.domain.Radio

fun RadioSearchResponseDto.toRadio(): Radio {
    return Radio(
        id = id ,
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