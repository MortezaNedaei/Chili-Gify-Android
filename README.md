# Chili Gify Android

This is a simple Android application that allows users to search for GIFs using the Giphy API.

## Technology
- [x] Android Studio Otter 2
- [x] **Single Activity**
- [x] **Jetpack Compose**-
- [x] **Dagger Hilt**- 
- [x] **Retrofit**
- [x] **Coil**
- [x] **Paging 3**
- [x] **Kotlin 2.2.21**
- [x] **KSP (Kotlin Symbol Processing)**: is a library for processing Kotlin source files.
- [x] MVVM
- [x] Coroutines
- [x] Navigation (**SafeArgs**)
- [x] Material Design 3
- [x] Portrait + Landscape
- [x] UnitTests (Mockk): is a library for mocking and verifying Kotlin code.
- [x] Network Error & Retry

### Screen Shots

## Structure

The application is structured into the following packages:

- **data:** Contains the data layer of the application, including the Giphy API service, repository, and models.
- **di:** Contains the Dagger Hilt modules for dependency injection.
- **ui:** Contains the UI layer of the application, including the Composable screens and ViewModels.

## How to Run

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Replace the `GIPHY_API_KEY` in `app/build.gradle.kts` with your own API key.
4.  Run the application.

### Backlog

- [ ] Improve Lag and performance of scrolling
- [ ] Add more unit tests
- [ ] Improve Rotation behavior
- [ ] Multi Module
- [ ] Create SDK
- [ ] Add Swipe Refresh
