package org.onedroid.radiowave.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.onedroid.radiowave.app.player.PlayerController
import org.onedroid.radiowave.data.database.DatabaseFactory
import org.onedroid.radiowave.data.datastore.dataStorePreferences

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single { DatabaseFactory() }
        singleOf(::PlayerController)
        singleOf(::dataStorePreferences)
    }