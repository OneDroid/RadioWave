package org.onedroid.radiowave.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.onedroid.radiowave.app.player.PlayerController
import org.onedroid.radiowave.data.database.DatabaseFactory

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory(androidApplication()) }
        single<HttpClientEngine> { OkHttp.create() }
        singleOf(::PlayerController)
    }