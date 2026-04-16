package br.com.iuri.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNav(
    manager: NavManager = viewModel()
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        manager.command.collect { action ->
            when (action) {
                is NavAction.NavigateTo -> {
                    navController.navigate(action.screen)
                }

                is NavAction.NavigateUp -> {
                    navController.popBackStack()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = ""
    ) {
    }
}