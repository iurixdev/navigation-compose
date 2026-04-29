package br.com.iuri.compose.feature_onboarding.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.iuri.compose.navigation.core.graph.FeatureNavGraph
import br.com.iuri.compose.navigation.core.routes.onboarding.OnboardingRoute

class OnboardingNavGraph : FeatureNavGraph {

    override fun register(builder: NavGraphBuilder) = builder.run {
        navigation<OnboardingRoute>(
            startDestination = OnboardingRoute.Welcome
        ) {
            composable<OnboardingRoute.Welcome> {

            }
            composable<OnboardingRoute.Features> {

            }
            composable<OnboardingRoute.Finish> {

            }
        }
    }
}
