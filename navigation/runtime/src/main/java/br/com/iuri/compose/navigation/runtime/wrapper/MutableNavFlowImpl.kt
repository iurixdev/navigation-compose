package br.com.iuri.compose.navigation.runtime.wrapper

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Internal concrete implementation of [MutableNavFlow].
 *
 * This class utilizes Kotlin's **Class Delegation** (`by` keyword) to implement the
 * [MutableSharedFlow] interface by wrapping a delegate instance. This approach
 * ensures that all standard flow behaviors are preserved while satisfying the
 * specialized [NavFlow] type contract.
 *
 * It is marked as `internal` to prevent direct instantiation from other modules,
 * enforcing the use of the [MutableNavFlow] factory function for better abstraction.
 *
 * @param T The type of navigation action.
 * @param delegate The underlying [MutableSharedFlow] that manages the emission
 * and collection of events.
 */
@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
internal class MutableNavFlowImpl<T>(
    delegate: MutableSharedFlow<T>
) : MutableNavFlow<T>, MutableSharedFlow<T> by delegate
