package br.com.iuri.compose.navigation.core.graph

import androidx.navigation.NavGraphBuilder


/**
 * Interface responsible for defining a modular navigation graph for a specific feature.
 *
 * Each feature module must implement this interface to register its own routes
 * and components into the application's main graph, ensuring strict decoupling
 * between modules.
 *
 * #### Example Implementation:
 * ```kotlin
 * internal class HomeNavGraph : FeatureNavGraph {
 *     override fun register(builder: NavGraphBuilder) {
 *         builder.composable<HomeRoute> {
 *             HomeScreen()
 *         }
 *     }
 * }
 * ```
 */
interface FeatureNavGraph {

    /**
     * Registers the feature's navigation destinations into the provided [NavGraphBuilder].
     *
     * This method is typically invoked by the central navigation system (via NavManager)
     * during the construction of the application's NavHost.
     *
     * @param builder The navigation graph builder where routes will be injected.
     */
    fun register(builder: NavGraphBuilder)
}
