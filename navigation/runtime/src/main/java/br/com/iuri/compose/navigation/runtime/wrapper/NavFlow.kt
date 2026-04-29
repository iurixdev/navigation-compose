package br.com.iuri.compose.navigation.runtime.wrapper

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow

/**
 * A specialized, read-only version of [SharedFlow] dedicated to navigation event streams.
 *
 * This interface serves as a semantic contract for observing navigation actions within
 * the application. By using a specific type for navigation, it differentiates navigation
 * triggers from general data state updates, improving code readability and architectural
 * consistency.
 *
 * Typically, this flow is exposed by a `NavManager` and collected in the UI layer
 * (usually via a `LaunchedEffect`) to execute navigation commands through a `NavController`.
 *
 * ### Why use a specialized interface:
 * - **Semantic Separation**: Clearly identifies the purpose of the stream in large codebases.
 * - **Architectural Safety**: Ensures that the UI layer can only observe events,
 *   preventing accidental emissions from the view layer.
 *
 * @param T The type of navigation action, usually representing screen destinations or back events.
 */
@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
interface NavFlow<T> : SharedFlow<T>
