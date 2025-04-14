package org.onedroid.radiowave.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.onedroid.radiowave.app.utils.IOSRadioBrowserBaseUrlProvider
import org.onedroid.radiowave.app.utils.RadioBrowserBaseUrlProvider

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single<RadioBrowserBaseUrlProvider> { IOSRadioBrowserBaseUrlProvider() }
    }