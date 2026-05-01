package br.com.iuri.compose.feature_login.screen.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.iuri.compose.feature_login.screen.screen.SampleLoginScreen
import br.com.iuri.compose.navigation.core.graph.FeatureNavGraph
import br.com.iuri.compose.navigation.core.routes.login.LoginRoute

internal class LoginNavGraph : FeatureNavGraph {
    override fun register(builder: NavGraphBuilder) {
        builder.composable<LoginRoute.Login> {
            SampleLoginScreen()
        }
    }
}