package br.com.iuri.compose.feature_onboarding.di

import br.com.iuri.compose.feature_onboarding.graph.OnboardingNavGraph
import br.com.iuri.compose.feature_onboarding.viewmodel.OnboardingViewModel
import br.com.iuri.compose.navigation.core.graph.FeatureNavGraph
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {
    viewModelOf(::OnboardingViewModel)
    singleOf(::OnboardingNavGraph) { bind<FeatureNavGraph>() }
}