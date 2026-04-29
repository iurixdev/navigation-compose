package br.com.iuri.compose.navigation.core.manager

import br.com.iuri.compose.navigation.runtime.wrapper.MutableNavFlow
import br.com.iuri.compose.navigation.runtime.wrapper.NavFlow
import br.com.iuri.compose.navigation.runtime.wrapper.asNavFlow

/**
 * Central coordinator responsible for managing and dispatching navigation commands across the application.
 *
 * The [NavManager] serves as a UI-independent bridge that allows business logic (ViewModels)
 * to trigger navigation events without having a direct dependency on the Android View system
 * or the [androidx.navigation.NavController].
 *
 * It uses a specialized [NavFlow] to expose navigation actions as a stream of events,
 * ensuring that navigation intents are handled in a thread-safe and lifecycle-aware manner
 * when collected by the UI layer.
 *
 * ### Key Responsibilities:
 * - **Decoupling**: Separates navigation logic from the Compose UI tree.
 * - **Event Dispatching**: Provides a thread-safe mechanism to emit [NavAction] intents.
 * - **State Independence**: Maintains navigation flow independently of screen rotations or
 *   configuration changes.
 */
class NavManager {
    /**
     * Internal mutable source for navigation commands.
     * Uses [MutableNavFlow] with a buffer to ensure events are captured even if the
     * UI is busy.
     */
    private val _command = MutableNavFlow<NavAction>()

    /**
     * Read-only stream of navigation commands to be observed by the UI layer.
     * Features and core navigation hosts should subscribe to this flow to execute
     * actual navigation operations.
     */
    val command: NavFlow<NavAction> = _command.asNavFlow()

    /**
     * Dispatches a navigation action to be processed by the navigation system.
     *
     * This method uses `tryEmit` to send the action immediately without suspending,
     * making it safe to call from any thread or scope.
     *
     * @param action The specific [NavAction] intent to be executed (e.g., NavigateTo, PopTo).
     */
    fun navigate(action: NavAction) {
        _command.tryEmit(action)
    }
}
