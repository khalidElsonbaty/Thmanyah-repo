# HomeSearchApp Documentation


## Overview

HomeSearchApp is an Android application built using Kotlin, Jetpack Compose, MVVM Architecture**, and Clean Architecture principles.
The app dynamically displays sections of different content types such as:

*  Podcasts
*  Episodes
*  Audiobooks
*  AudioArticles

It features a Home Screen that loads dynamic sections and a Search Screen with delayed request execution to minimize server load.

---

## Technologies & Tools

* Kotlin (Language)
* Jetpack Compose (UI)
* Retrofit + Kotlinx Serialization (Networking)
* Kotlin Coroutines / Flow (Async / Reactive streams)
* Hilt (Dependency Injection)
* Unit Tests (UseCase and Repository level)
* MVVM (Presentation architecture)
* Version Catalog (libs.versions.toml) for dependency management

---

## Project Structure

- data
  - remote
    - api/              // Retrofit interfaces
    - dto/              // API models
  - repository/         // Repository implementation

- domain
  - model/              // App core models
  - useCase/            // Use cases
  - mapper/             //   domain mapping

- presentation
  - home/               // Home screen and ViewModel
  - search/             // Search screen and ViewModel
  - components/         // Composable shared in screens

- di/                   // Hilt modules (e.g., NetworkModule)
- MainActivity.kt       // App launcher
- MyApplication.kt      // Application class with @HiltAndroidApp

---

## App Flow

### Home Screen:

* Fetches data from: `https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections`
* Displays multiple vertical sections.
* Each section has:

  * A title.
  * A horizontal list (`LazyRow`) using either grid or linear layout based on type.

### Search Screen:

* Input field for user text.
* Uses `debounce(200ms)` before firing request.
* Fetches from: `https://mock.apidog.com/m1/735111-711675-default/search`
* Displays results with the same layout logic as Home screen.



## Testing

* **Unit Tests** for UseCases and Repositories using mocked dependencies.
* **UI Testing (optional)** using `androidx.compose.ui:ui-test-junit4`.

---

## Challenges

* Dynamic rendering of multiple section types (linear/grid).
* Mapping inconsistent API data into stable domain models.
* Managing debounce & cancellation of search requests.

---

## Improvements & Suggestions

* Use **Paging 3** for true infinite scroll per section.
* Add caching layer (e.g., Room or in-memory) to reduce network calls.
* Add error handling with `sealed` UI states (Loading / Success / Error).
* Add support for Dark Mode and Theming.
* Add Preview annotations for UI components.

---


## Final Notes

This app demonstrates a clean, modular, and scalable approach to building a modern Android app with Compose UI and Kotlin best practices. The architecture supports separation of concerns, testability, and easy maintainability.
