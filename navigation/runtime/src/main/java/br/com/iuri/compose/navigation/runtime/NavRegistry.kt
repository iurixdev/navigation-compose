package br.com.iuri.compose.navigation.runtime

import androidx.navigation.NavGraphBuilder
import br.com.iuri.compose.navigation.runtime.graph.FeatureNavGraph

/**
 * A registry for coordinating and assembling multiple feature-specific navigation graphs.
 *
 * The [NavRegistry] acts as a central collector that holds a list of [br.com.iuri.compose.navigation.core.graph.FeatureNavGraph]
 * implementations. Its primary role is to bridge the gap between individual feature
 * modules and the main application's navigation structure.
 *
 * This component is essential for maintaining a decoupled architecture, as the
 * runtime system doesn't need to know the internal routes of each feature module;
 * it only needs to know how to trigger their registration.
 *
 * @property _graphs The internal list of feature-specific navigation graphs to be registered.
 */
class NavRegistry(
    private val _graphs: List<FeatureNavGraph> = emptyList()
) {
    /**
     * Registers all contained feature graphs into the provided [NavGraphBuilder].
     *
     * This method iterates over the internal collection of graphs and invokes their
     * [br.com.iuri.compose.navigation.core.graph.FeatureNavGraph.register] method, effectively injecting all feature-defined
     * routes into the application's navigation hierarchy.
     *
     * @param builder The [NavGraphBuilder] used to assemble the navigation graph.
     */
    fun registerGraphs(builder: NavGraphBuilder) {
        _graphs.forEach { it.register(builder) }
    }
}
