# BowlApp

BowlApp is a Kotlin Multiplatform app targeting Android and iOS built with ComposeMultiplatform to share UI across Android and iOS, Ktor Client for networking and Room for persistence

## Code Structure

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


## How to Build
  1. Make sure Android Studio( install KMM and Compose Multiplatform plugins) and XCode are installed
  2. Open project in Android Studio
  3. Build both iOS and Android app from the Build Configuration of Studio itself.
  4. iOS app can also be built in XCode, for that open "project.xcworkspace" folder inside "iosApp" in Xcode and build.

## Technologies Used
  1. Compose Multiplatform for sharing UI across Android and iOS (alpha)
  2. Room DB for persistence (in alpha, supporting KMP)
  3. Android Jetpack ViewModel (in alpha, supporting KMP)
  4. Koin for DI
  5. KtorClient for networking

## Screenshots

## Android

<img src="https://github.com/Vendhan/Bowl/assets/14262888/3498eb27-baa4-44fe-b668-149cfcc0b9bf" width="360" height="800" />


<img src="https://github.com/Vendhan/Bowl/assets/14262888/524f3ab2-dbd2-4a06-87fa-eae7d32a0ee0" width="360" height="800" />


## iOS

<img src="https://github.com/Vendhan/Bowl/assets/14262888/845dca21-267d-4501-afb5-3a15c26ee77a" width="360" height="800" />


<img src="https://github.com/Vendhan/Bowl/assets/14262888/dc7fa937-34bd-4450-b099-cff0d2cb9fe0" width="360" height="800" />


## Useful Links

1. Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)

2. [Room supports KMP](https://developer.android.com/kotlin/multiplatform/room)

3. [KMM Movies App built using KMM with native UI code and shared business logic] (https://github.com/Vendhan/KMMMoviesApp)

