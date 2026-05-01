package br.com.iuri.compose.navigation.core.routes.login

import kotlinx.serialization.Serializable

sealed class LoginRoute {
    @Serializable
    data object Login : LoginRoute()
}