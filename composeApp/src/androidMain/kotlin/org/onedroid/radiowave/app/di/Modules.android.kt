package org.onedroid.radiowave.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.onedroid.radiowave.app.player.PlayerController

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        singleOf(::PlayerController)
    }