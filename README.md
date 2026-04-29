# Navigation Architecture with NavManager

A clean, decoupled, and highly scalable navigation framework for Android applications built with *
*Jetpack Compose**, **Koin**, and **Kotlin Serialization**.

## 🚀 Overview

This architecture moves navigation logic out of the UI layer and into the business logic (
ViewModels). By using a centralized `NavManager`, features remain completely isolated, routes are
type-safe, and navigation events are lifecycle-aware.

## 🏗️ Modular Architecture

The system is split into two sub-modules to enforce strict encapsulation and optimize build times:

### 1. `:navigation:core` (The Contract)

The "thin" layer that all feature modules depend on. It contains no Android UI dependencies.

- **`NavManager`**: Interface used by ViewModels to dispatch navigation intents.
- **`NavAction`**: A rich sealed class defining *what* the navigation should do (e.g., `NavigateTo`,
  `ReplaceGraph`, `SwitchTab`).
- **`NavFlow<T>`**: A specialized, read-only `SharedFlow` wrapper for navigation streams.
- **`FeatureNavGraph`**: The contract for feature modules to register their own routes.
- **`Routes`**: Type-safe `@Serializable` objects defining destinations.

### 2. `:navigation:runtime` (The Engine)

The implementation layer. Only the `:app` module (and the navigation host) needs to know about this.

- **`NavManagerImpl`**: The concrete implementation managing the `MutableNavFlow`.
- **`BindNavManager`**: A lifecycle-aware extension that connects the `NavController` to the
  `NavManager`.
- **`NavRegistry`**: A central collector that assembles all `FeatureNavGraph` instances into the
  `NavHost`.
- **`AppNav` & `AppHost`**: The top-level Composables that initialize the navigation container.

## 🛠️ Key Components

### Declarative Navigation Intents (`NavAction`)

Instead of managing `NavOptions` in ViewModels, we use declarative actions:

- **`NavigateTo`**: Standard navigation with `singleTop` support.
- **`ReplaceGraph`**: Clears the entire backstack (e.g., Login ➔ Home).
- **`SwitchTab`**: Optimized for Bottom Navigation with state preservation.
- **`PopTo`**: Targeted backstack removal.

### Specialized Flow (`NavFlow`)

We utilize a custom `NavFlow` (built on `SharedFlow`) with a default buffer of 1. This ensures that:

1. Navigation commands are never lost if emitted while the UI is busy.
2. Commands are delivered exactly once (Event-based, not State-based).
3. The UI layer cannot accidentally emit events.

## 💻 How to Use

### 1. Define Feature Routes

### 2. Trigger from ViewModel

### 3. Bind in the UI
