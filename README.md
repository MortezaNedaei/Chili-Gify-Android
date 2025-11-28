# Chili Gify Android

This is a simple Android application that allows users to search for GIFs using the Giphy API.

## Demo App Link: 

Download the release Demo app from [this link](https://github.com/MortezaNedaei/Chili-Gify-Android/blob/master/Chili-Giphy-1.0-universal-release.apk) 

## Technology

- ✅ Android Studio Otter 2
- ✅ **Single Activity**
- ✅ **Jetpack Compose**-
- ✅ **Dagger Hilt**-
- ✅ **Retrofit**
- ✅ **Coil**
- ✅ **Paging 3**
- ✅ **Kotlin 2.2.21**
- ✅ **KSP (Kotlin Symbol Processing)**: is a library for processing Kotlin source files.
- ✅ MVVM
- ✅ Coroutines
- ✅ Navigation (**SafeArgs**)
- ✅ Material Design 3
- ✅ Portrait + Landscape
- ✅ UnitTests (Mockk): is a library for mocking and verifying Kotlin code.
- ✅ Network Error & Retry
- ✅ Rotation support
- ✅ Caching (RAM & Disk)
- ✅ Improve performance by using `fixed_width_still` images. This allows us to correctly parse the
  URL for the static preview image from the API response.

### Screen Shots

## Structure

The application is structured into the following packages:

- **data:** Contains the data layer of the application, including the Giphy API service, repository,
  and models.
- **di:** Contains the Dagger Hilt modules for dependency injection.
- **ui:** Contains the UI layer of the application, including the Composable screens and ViewModels.

## How to Run

1. Clone the repository.
2. Open the project in Android Studio.
3. Replace the `GIPHY_API_KEY` in `app/build.gradle.kts` with your own API key.
4. Run the application.

## How to release

```shell
 ./gradlew assembleRelease
```

## Backlog

- ✅ Improve rotation performance and caching mechanism in memory and disk.
- ✅ Improve performance of scrolling in **Debug** variant. **Release mode is very smooth**.
- [ ] Add more unit tests
- [ ] Multi Module
- [ ] Create SDK
- [ ] Add Swipe Refresh
- [ ] Add CI workflows

## Notes:

- I have used the test keys in `app/gradle.properties` file for `release.keystore` configuration.
  these keys should be stored in secrets for production level apss.
- using `fixed_width_still` image had a better performance on my tests. But I have also defined
  `fixedWidthDownsampled` and `webp` images in `GifData`
  that we can use if required.
- The performance difference reason between debug and release builds is that Debug builds are
  intentionally not optimized for performance. They are designed to help you debug the app, and are
  packed with extra features that have a significant performance cost. Since they
  contain a large amount of extra metadata, logging, and hooks for the
  debugger to attach to. So disabling all loggers like OkHttp logger can improve the performance.
- the Giphy items may not have unique IDs. This can makes the app crash. That's why I have created a
  unique id by using this code: `val uniqueId = id + Random.nextInt().toString()`