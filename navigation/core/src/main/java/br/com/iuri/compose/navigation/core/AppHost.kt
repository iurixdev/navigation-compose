package br.com.iuri.compose.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.iuri.compose.navigation.core.routes.onboarding.OnboardingRoute
import br.com.iuri.compose.navigation.core.NavRegistry
import org.koin.compose.koinInject

/**
 * The internal navigation host responsible for assembling the application's global navigation graph.
 *
 * This composable initializes the [NavHost] and integrates modular feature graphs provided by the [NavRegistry].
 * It serves as the physical container where different screen destinations are swapped based on the
 * current navigation state.
 *
 * The implementation relies on the [NavRegistry] to dynamically inject routes from decoupled feature
 * modules, ensuring the host itself remains agnostic of the specific internal screens of each module.
 *
 * @param modifier The [Modifier] applied to the underlying [NavHost] layout.
 * @param navController The [NavHostController] that manages the backstack and navigation state.
 * @param registry The [NavRegistry], resolved via Koin, containing the collection of
 * [FeatureNavGraph] instances to be registered within this host.
 */
@Composable
internal fun AppHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    registry: NavRegistry = koinInject()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = OnboardingRoute.Welcome
    ) {
        registry.registerGraphs(this)
    }
}
