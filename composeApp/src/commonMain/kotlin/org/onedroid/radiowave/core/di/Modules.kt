package org.onedroid.radiowave.core.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.onedroid.radiowave.app.presentation.home.HomeViewModel

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::HomeViewModel)
}