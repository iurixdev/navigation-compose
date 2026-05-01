package br.com.iuri.compose.feature_login.screen.viewmodel

import androidx.lifecycle.ViewModel
import br.com.iuri.compose.navigation.core.manager.NavAction
import br.com.iuri.compose.navigation.core.manager.NavManager
import br.com.iuri.compose.navigation.core.routes.onboarding.OnboardingRoute

internal class LoginViewModel(
    private val navManager: NavManager
) : ViewModel() {
    fun onNavigateToWelcome() {
        navManager.navigate(NavAction.NavigateTo(OnboardingRoute.Welcome))
    }
}