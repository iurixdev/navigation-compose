package br.com.iuri.compose.feature_login.screen.di

import br.com.iuri.compose.feature_login.screen.graph.LoginNavGraph
import br.com.iuri.compose.feature_login.screen.viewmodel.LoginViewModel
import br.com.iuri.compose.navigation.core.graph.FeatureNavGraph
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
    singleOf(::LoginNavGraph) { bind<FeatureNavGraph>() }
}