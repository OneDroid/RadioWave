package org.onedroid.radiowave.app.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.onedroid.radiowave.data.network.RemoteRadioDataSource
import org.onedroid.radiowave.data.network.RemoteRadioDataSourceImpl
import org.onedroid.radiowave.data.repository.RadioRepositoryImpl
import org.onedroid.radiowave.domain.RadioRepository
import org.onedroid.radiowave.presentation.home.HomeViewModel
import org.onedroid.radiowave.app.utils.HttpClientFactory

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteRadioDataSourceImpl).bind<RemoteRadioDataSource>()
    singleOf(::RadioRepositoryImpl).bind<RadioRepository>()
    viewModelOf(::HomeViewModel)
}