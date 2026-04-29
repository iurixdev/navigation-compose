package br.com.iuri.compose.navigation.runtime.wrapper

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow


/**
 * A specialized, mutable version of [NavFlow] designed for handling navigation event streams.
 *
 * This interface combines the read-only [NavFlow] contract with the [MutableSharedFlow]
 * capabilities, allowing for thread-safe emission of navigation actions. It is typically
 * used within a `NavManager` implementation as a private source of truth.
 *
 * ### Why use this specialized flow:
 * - **Semantic Clarity**: Distinguishes navigation event streams from general data flows.
 * - **Encapsulation**: Simplifies the internal management of navigation commands before
 * exposing them as read-only streams.
 *
 * ### Usage Example:
 * ```kotlin
 * class NavManagerImpl : NavManager {
 *     // Internal mutable source
 *     private val _command = MutableNavFlow<NavAction>()
 *
 *     // Exposed as read-only NavFlow
 *     override val command: NavFlow<NavAction> = _command.asNavFlow()
 *
 *     override fun navigate(action: NavAction) {
 *         _command.tryEmit(action)
 *     }
 * }
 * ```
 *
 * @param T The type of navigation action, typically a sealed class or interface.
 */
@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
interface MutableNavFlow<T> : NavFlow<T>, MutableSharedFlow<T>
