package br.com.iuri.compose.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.rememberNavController
import br.com.iuri.compose.navigation.core.manager.NavManager
import org.koin.compose.koinInject

/**
 * The root navigation component that initializes the [NavController] and binds it to the [NavManager].
 *
 * This composable acts as the primary bridge between the application's business logic and its
 * UI navigation layer. It orchestrates the lifecycle of the [NavController] and leverages the
 * `BindNavManager` extension to reactively process navigation commands emitted from outside
 * the UI layer.
 *
 * By delegating the route definitions and graph assembly to the [AppHost], this component
 * maintains a clean separation between navigation coordination and content hosting.
 *
 * @param modifier The [Modifier] to be applied to the top-level navigation container.
 * @param manager The [NavManager] instance, resolved via Koin, which serves as the
 * single source of truth for navigation events across all feature modules.
 */
@Composable
fun AppNav(
    modifier: Modifier = Modifier,
    manager: NavManager = koinInject()
) {
    val navController = rememberNavController()

    navController.BindNavManager(manager)

    AppHost(
        modifier = modifier.testTag("app_host"),
        navController = navController
    )
}