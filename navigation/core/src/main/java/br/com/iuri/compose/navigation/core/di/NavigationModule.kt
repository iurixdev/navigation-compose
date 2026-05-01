package br.com.iuri.compose.navigation.core.di

import br.com.iuri.compose.navigation.core.NavRegistry
import br.com.iuri.compose.navigation.core.manager.NavManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {
    singleOf(::NavManager)
    single { NavRegistry(getAll()) }
}