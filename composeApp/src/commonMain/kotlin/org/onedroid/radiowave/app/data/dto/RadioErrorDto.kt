package org.onedroid.radiowave.app.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RadioErrorDto(
    @SerialName("message")
    val message: String? = null,
)