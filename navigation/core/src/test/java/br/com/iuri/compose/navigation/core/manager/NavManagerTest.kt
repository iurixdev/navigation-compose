package br.com.iuri.compose.navigation.core.manager

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NavManagerTest {
    private val navManager = NavManager()

    @Test
    fun `when navigate is called, the command flow should emit the correct action`() = runTest(
        UnconfinedTestDispatcher()
    ) {
        val expectedAction = NavAction.NavigateTo("home_screen")

        val deferred = async {
            navManager.command.first()
        }

        navManager.navigate(expectedAction)

        val actualAction = deferred.await()
        Assert.assertEquals(expectedAction, actualAction)

    }

    @Test
    fun `when navigate is called, the command flow should emit NavigateUp`() = runTest(
        UnconfinedTestDispatcher()
    ) {
        val deferred = async { navManager.command.first() }

        navManager.navigate(NavAction.NavigateUp)

        Assert.assertEquals(NavAction.NavigateUp, deferred.await())
    }

    @Test
    fun `when navigate is called, the command flow should emit ReplaceGraph with correct screen`() =
        runTest(
            UnconfinedTestDispatcher()
        ) {
            val expectedScreen = "login_screen"
            val deferred = async { navManager.command.first() }

            navManager.navigate(NavAction.ReplaceGraph(expectedScreen))

            val actualAction = deferred.await() as NavAction.ReplaceGraph
            Assert.assertEquals(expectedScreen, actualAction.screen)
        }

    @Test
    fun `when navigate is called, the command flow should emit PopTo with correct parameters`() =
        runTest(
            UnconfinedTestDispatcher()
        ) {
            val expectedRoute = "home_screen"
            val expectedInclusive = true
            val deferred = async { navManager.command.first() }

            navManager.navigate(NavAction.PopTo(expectedRoute, inclusive = expectedInclusive))

            val actualAction = deferred.await() as NavAction.PopTo
            Assert.assertEquals(expectedRoute, actualAction.route)
            Assert.assertEquals(expectedInclusive, actualAction.inclusive)
        }

    @Test
    fun `when navigate is called, the command flow should emit SwitchTab with correct screen`() =
        runTest(
            UnconfinedTestDispatcher()
        ) {
            val expectedTab = "profile_tab"
            val deferred = async { navManager.command.first() }

            navManager.navigate(NavAction.SwitchTab(expectedTab))

            val actualAction = deferred.await() as NavAction.SwitchTab
            Assert.assertEquals(expectedTab, actualAction.screen)
        }
}