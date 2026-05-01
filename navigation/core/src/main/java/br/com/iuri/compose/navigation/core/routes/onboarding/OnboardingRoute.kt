package br.com.iuri.compose.navigation.core.routes.onboarding

import kotlinx.serialization.Serializable

sealed class OnboardingRoute {
    @Serializable
    data object Welcome : OnboardingRoute()

    @Serializable
    data object Features : OnboardingRoute()

    @Serializable
    data object Finish : OnboardingRoute()
}