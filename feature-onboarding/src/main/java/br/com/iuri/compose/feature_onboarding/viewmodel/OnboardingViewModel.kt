package br.com.iuri.compose.feature_onboarding.viewmodel

import androidx.lifecycle.ViewModel
import br.com.iuri.compose.navigation.core.manager.NavAction
import br.com.iuri.compose.navigation.core.manager.NavManager
import br.com.iuri.compose.navigation.core.routes.login.LoginRoute
import br.com.iuri.compose.navigation.core.routes.onboarding.OnboardingRoute

internal class OnboardingViewModel(
    private val navManager: NavManager
) : ViewModel() {
    fun onNavigateToFeatures() {
        navManager.navigate(NavAction.NavigateTo(OnboardingRoute.Features))
    }

    fun onNavigateToFinish() {
        navManager.navigate(NavAction.NavigateTo(OnboardingRoute.Finish))
    }

    fun onNavigateToLogin() {
        navManager.navigate(NavAction.ReplaceGraph(LoginRoute.Login))
    }
}