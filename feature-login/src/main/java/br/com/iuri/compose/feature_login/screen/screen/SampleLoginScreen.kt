package br.com.iuri.compose.feature_login.screen.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.iuri.compose.feature_login.screen.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SampleLoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Login!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { viewModel.onNavigateToWelcome() }
        ) {
            Text(text = "Go to Onboarding")
        }
    }
}