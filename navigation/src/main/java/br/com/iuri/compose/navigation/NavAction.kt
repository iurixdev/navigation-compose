package br.com.iuri.compose.navigation

sealed class NavAction {
    object NavigateUp : NavAction()
    data class NavigateTo(val screen: NavScreen) : NavAction()
}