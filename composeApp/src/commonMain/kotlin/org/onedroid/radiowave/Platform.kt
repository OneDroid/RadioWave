package org.onedroid.radiowave

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform