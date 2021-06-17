# 🚀 Compose Arcade

A sample Kotlin Multiplatform Compose Sudoku app for Android & Desktop.
Most code is shared between Android & Desktop using Kotlin Multiplatform and [Jetbrains Compose](https://github.com/JetBrains/compose-jb).

![Alt text](screenshots/sudoku-demo.png?raw=true "Demo running on Android & Ubuntu")


### This example supports the following targets: 
- `Android` (Compose)
- `JVM` (Compose - Win / Linux / MacOS)

### Libraries used:
- [Jetpack Compose](https://github.com/JetBrains/compose-jb) - shared UI
- [Decompose](https://github.com/arkivanov/Decompose) - navigation and lifecycle
- [Mavericks](https://github.com/airbnb/mavericks) - state management using MVI. For this project I created a 
  minimalistic multi-platform ViewModel implementation using MavericksStateStore.
- [Sudoku Game Logic](https://github.com/a11n/sudoku) A Sudoku generator and solver, ported to Kotlin multi-platform.

### There are multiple modules:
- `:common:sudoku` - displays a list of todo items and a text field
- `:common:utils` - some shared utilities, including the state management classes
- `:common:root` - UI entrypoint and navigation logic
- `:common:compose-ui` - platform specific UI implementations (expect/actual funs)
- `:android` - Android application
- `:desktop` - Desktop application

The root module is integrated into Android and Desktop apps.

### Features:
- 99% of the code is shared: data, business logic, presentation, navigation and UI
- View state is preserved when navigating between screens, Android configuration change, etc.
- Model-View-Intent (aka MVI) architectural pattern

### Running desktop application
```
./gradlew :desktop:run
```

### Building native desktop distribution
```
./gradlew :desktop:package
# outputs are written to desktop/build/compose/binaries
```

### Running Android application

Open project in Intellij IDEA or Android Studio and run "android" configuration.