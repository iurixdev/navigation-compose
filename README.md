# Navigation Architecture with NavManager

This project demonstrates a cleaner, decoupled, and scalable navigation approach in
Android applications using Jetpack Compose, with navigation being controlled by a ViewModel
through a centralized NavManager.

## Motivation

Default navigation using NavController can become hard to maintain when:

- Navigation logic is spread across multiple screens
- Composables become tightly coupled to routes
- Testability is reduced
- Flow control (e.g., login → home) is handled inside the UI

This project solves these issues by centralizing navigation into a single source of truth.

## Core Idea

Navigation is not triggered directly by the UI.

Instead:

- The UI sends intents
- The ViewModel decides what to do
- The NavManager emits navigation commands
- The NavHost listens and executes

## 🏗️ Components

### NavManager
Responsible for emitting navigation actions.

```kotlin
sealed class NavAction {
    data class NavigateTo(val screen: NavScreen) : NavAction()
    object NavigateUp : NavAction()
}

class NavManager {
    private val _command = MutableSharedFlow<NavAction>()
    val command = _command.asSharedFlow()

    suspend fun navigate(action: NavAction) {
        _command.emit(action)
    }
}
```
### NavHost
Observes navigation events and performs navigation.

```kotlin
LaunchedEffect(Unit) {
    navManager.command.collect { action ->
        when (action) {
            is NavAction.NavigateTo -> {
                navController.navigate(action.screen.route)
            }
            is NavAction.NavigateUp -> {
                navController.popBackStack()
            }
        }
    }
}
```

## Conclusion
Using a ViewModel-driven navigation with a centralized NavManager improves organization, 
testability, and scalability, making it a solid choice for modern Android applications.
