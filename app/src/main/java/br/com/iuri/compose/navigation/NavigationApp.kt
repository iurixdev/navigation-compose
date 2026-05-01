package br.com.iuri.compose.navigation

import android.app.Application
import br.com.iuri.compose.feature_login.screen.di.loginModule
import br.com.iuri.compose.feature_onboarding.di.onboardingModule
import br.com.iuri.compose.navigation.core.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NavigationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NavigationApp)

            modules(
                navigationModule,
                onboardingModule,
                loginModule
            )
        }
    }
}