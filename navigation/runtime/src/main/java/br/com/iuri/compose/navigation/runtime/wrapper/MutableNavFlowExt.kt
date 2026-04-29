package br.com.iuri.compose.navigation.runtime.wrapper

import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Factory function to create a [MutableNavFlow] with optimized configurations for navigation.
 *
 * By default, it initializes with an [extraBufferCapacity] of 1. This is the "sweet spot" for
 * navigation as it allows for non-suspending emissions via `tryEmit`, ensuring that
 * navigation commands are captured even if the UI is momentarily processing another task.
 *
 * @param T The type of navigation action.
 * @param replay The number of values replayed to new subscribers. Defaults to 0 since
 * navigation events are usually one-time triggers.
 * @param extraBufferCapacity The capacity of the buffer in addition to replay. Defaults to 1
 * to enable non-blocking navigation calls.
 * @return A new instance of [MutableNavFlow] backed by a [MutableSharedFlow].
 */
fun <T> MutableNavFlow(
    replay: Int = 0,
    extraBufferCapacity: Int = 1
): MutableNavFlow<T> = MutableNavFlowImpl(
    MutableSharedFlow(replay, extraBufferCapacity)
)

/**
 * Converts a [MutableNavFlow] into its read-only [NavFlow] counterpart.
 *
 * Use this extension to follow the encapsulation principle: keep a mutable source private
 * within your manager or logic class, and expose only the immutable stream to the UI.
 *
 * ### Example:
 * ```kotlin
 * private val _command = MutableNavFlow<NavAction>()
 * val command: NavFlow<NavAction> = _command.asNavFlow()
 * ```
 *
 * @param T The type of navigation action.
 * @return The current instance cast as a read-only [NavFlow].
 */
fun <T> MutableNavFlow<T>.asNavFlow(): NavFlow<T> = this
