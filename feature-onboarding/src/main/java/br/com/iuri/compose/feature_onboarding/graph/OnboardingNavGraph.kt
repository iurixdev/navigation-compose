package br.com.iuri.compose.feature_onboarding.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.iuri.compose.feature_onboarding.screen.SampleFeaturesScreen
import br.com.iuri.compose.feature_onboarding.screen.SampleFinishScreen
import br.com.iuri.compose.feature_onboarding.screen.SampleWelcomeScreen
import br.com.iuri.compose.navigation.core.graph.FeatureNavGraph
import br.com.iuri.compose.navigation.core.routes.onboarding.OnboardingRoute

internal class OnboardingNavGraph : FeatureNavGraph {

    override fun register(builder: NavGraphBuilder) = builder.run {
        composable<OnboardingRoute.Welcome> {
            SampleWelcomeScreen()
        }
        composable<OnboardingRoute.Features> {
            SampleFeaturesScreen()
        }
        composable<OnboardingRoute.Finish> {
            SampleFinishScreen()
        }
    }
}
