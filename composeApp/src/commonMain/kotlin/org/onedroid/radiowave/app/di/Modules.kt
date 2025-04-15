package org.onedroid.radiowave.app.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.onedroid.radiowave.app.player.PlayerRepository
import org.onedroid.radiowave.app.player.PlayerRepositoryImpl
import org.onedroid.radiowave.app.utils.AppPreferences
import org.onedroid.radiowave.app.utils.HttpClientFactory
import org.onedroid.radiowave.data.database.DatabaseFactory
import org.onedroid.radiowave.data.database.RadioWaveDatabase
import org.onedroid.radiowave.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.data.network.RemoteRadioDataSourceImpl
import org.onedroid.radiowave.data.repository.RadioRepositoryImpl
import org.onedroid.radiowave.domain.RadioRepository
import org.onedroid.radiowave.presentation.home.HomeViewModel
import org.onedroid.radiowave.presentation.settings.SettingViewModel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    single { get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build() }
    single { get<RadioWaveDatabase>().radioWaveDao }
    singleOf(::RemoteRadioDataSourceImpl).bind<RemoteRadioDataSource>()
    singleOf(::RadioRepositoryImpl).bind<RadioRepository>()
    singleOf(::PlayerRepositoryImpl).bind<PlayerRepository>()
    singleOf(::AppPreferences)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SettingViewModel)
}