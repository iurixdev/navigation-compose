package br.com.iuri.compose.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.iuri.compose.navigation.core.manager.NavAction
import br.com.iuri.compose.navigation.core.manager.NavManager


/**
 * Binds a [NavManager] to this [NavController], enabling lifecycle-aware navigation event processing.
 *
 * This extension function acts as the bridge between the architecture's navigation intentions
 * (represented by [NavAction] emitted from [NavManager]) and the actual Jetpack Navigation
 * execution on the UI layer.
 *
 * It uses [flowWithLifecycle] to ensure that navigation commands are only collected and
 * executed when the lifecycle is at least in the [Lifecycle.State.STARTED] state, preventing
 * crashes or illegal state transitions when the app is in the background.
 *
 * ### Navigation Logic:
 * - **Standard Navigation**: Maps [NavAction.NavigateTo] while respecting [NavAction.NavigateTo.singleTop]
 *   and state restoration flags.
 * - **Backstack Manipulation**: Directly handles [NavAction.NavigateUp] and [NavAction.PopTo].
 * - **Graph Replacement**: Clears the entire current graph before navigating to a new root,
 *   essential for "Login -> Home" transitions.
 * - **Tab Switching**: Implements the recommended pattern for Bottom Navigation, ensuring
 *   backstack state is saved and restored when toggling between tabs.
 *
 * @param navManager The [NavManager] instance whose command flow will be observed.
 */
@Composable
fun NavController.BindNavManager(navManager: NavManager) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(navManager, this) {
        navManager.command
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { action ->
                handleNavAction(action)
            }
    }
}

/**
 * Orchestrates the execution of [NavAction] intents into concrete [NavController] operations.
 *
 * This private helper encapsulates the mapping logic between abstract navigation commands
 * and the specific [androidx.navigation.NavOptions] required to achieve the desired
 * backstack behavior.
 *
 * @param action The specific navigation intent to be executed.
 */
private fun NavController.handleNavAction(action: NavAction) {
    when (action) {
        is NavAction.NavigateTo -> navigate(action.screen) {
            launchSingleTop = action.singleTop
            restoreState = action.restoreState
        }

        is NavAction.NavigateUp -> navigateUp()

        is NavAction.PopTo -> popBackStack(
            route = action.route,
            inclusive = action.inclusive,
            saveState = action.saveState
        )

        is NavAction.ReplaceGraph -> navigate(action.screen) {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
            }

            launchSingleTop = true
        }

        is NavAction.SwitchTab -> navigate(action.screen) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }
}
