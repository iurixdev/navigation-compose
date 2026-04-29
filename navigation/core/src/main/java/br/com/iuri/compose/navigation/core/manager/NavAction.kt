package br.com.iuri.compose.navigation.core.manager

/**
 * Defines the set of possible navigation actions within the application.
 *
 * This sealed class represents the "Intents" emitted by business logic (ViewModels)
 * to be processed by the navigation system. By using these specialized actions,
 * the application maintains a clear separation between deciding to navigate
 * and executing the navigation using Android-specific APIs.
 */
sealed class NavAction {
    /**
     * Navigates back to the previous destination in the backstack.
     */
    data object NavigateUp : NavAction()

    /**
     * Navigates to a specific screen destination with standard configuration options.
     *
     * @property screen The destination route object or class (must be @Serializable).
     * @property singleTop If true, avoids multiple instances of the destination on top of the stack.
     * @property restoreState Whether to restore the previously saved state of the destination.
     */
    data class NavigateTo(
        val screen: Any,
        val singleTop: Boolean = false,
        val restoreState: Boolean = false
    ) : NavAction()

    /**
     * Pops the backstack up to a specific destination.
     *
     * @property route The target destination to return to.
     * @property inclusive If true, also removes the [route] itself from the stack.
     * @property saveState Whether to save the state of the destinations being popped.
     */
    data class PopTo(
        val route: Any,
        val inclusive: Boolean = false,
        val saveState: Boolean = false
    ) : NavAction()

    /**
     * Replaces the entire current navigation graph with a new starting destination.
     *
     * This is typically used for major state transitions, such as moving from
     * an Onboarding/Login flow to the Main application flow, clearing all previous history.
     *
     * @property screen The new root destination of the application.
     */
    data class ReplaceGraph(val screen: Any) : NavAction()

    /**
     * Handles switching between top-level destinations, such as Bottom Navigation tabs.
     *
     * This action is optimized for tabbed interfaces, ensuring that the state of
     * each tab is preserved and restored during the transition.
     *
     * @property screen The tab destination to switch to.
     */
    data class SwitchTab(val screen: Any) : NavAction()
}
