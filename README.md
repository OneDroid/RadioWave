<div align="center">
<p><img src="https://github.com/OneDroid/RadioWave/blob/main/readme/web/icon-512.png" alt="RadioWave Logo" width="128"/></p>
<h3 align="center">RadioWave</h3>
  <p align="center">
RadioWave is a cross-platform internet radio player for Android and iOS, built with Kotlin Multiplatform. It provides access to over 50,000 stations worldwide with automatic song recognition that identifies what's playing in real time.
  </p>

[![License](https://img.shields.io/badge/license-GPLv3-yellow.svg)](LICENSE)
![GitHub Release](https://img.shields.io/github/v/release/OneDroid/RadioWave?logo=github)
![Fdroid Release](https://img.shields.io/f-droid/v/org.onedroid.radiowave.svg?logo=F-Droid)
![Static Badge](https://img.shields.io/badge/Platforms-Android%20%26%20iOS-8A2BE2)
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/OneDroid/RadioWave/total?logo=github&color=brightgreen)
![visitors](https://visitor-badge.laobi.icu/badge?page_id=OneDroid.RadioWave)
[![SavePalestine](https://raw.githubusercontent.com/OneDroid/.github/refs/heads/main/images/badge/save-palestine.svg)](https://techforpalestine.org/learn-more)

</div>

## Download
[<img src="https://raw.githubusercontent.com/OneDroid/.github/220b8baeb925e81df5a3757fa878432bfb645b8c/images/badge/badge-github.svg" alt="Download from GitHub" height="60">](https://github.com/OneDroid/RadioWave/releases)
[<img src="https://raw.githubusercontent.com/OneDroid/.github/220b8baeb925e81df5a3757fa878432bfb645b8c/images/badge/badge-obtainium.svg" alt="Download from Obtainium" height="60">](https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/OneDroid/RadioWave)
[<img src="https://raw.githubusercontent.com/OneDroid/.github/ea0fa1a07e882cb4c645931cc0659190a6eb7c69/images/badge/badge-openapk.svg" alt="Download from OpenApk" height="60">](https://www.openapk.net/radiowave/org.onedroid.radiowave/)
[<img src="https://raw.githubusercontent.com/OneDroid/.github/a6eb5b4c079f52c4651f605c79f426712ba4ae3a/images/badge/badge-fdroid.svg" alt="Download from F-droid" height="60">](https://f-droid.org/en/packages/org.onedroid.radiowave/)

SHA-256: `ED:1E:40:16:F2:4E:35:01:DD:C3:02:6B:AC:A0:4E:C8:C8:21:09:E7:48:EC:9F:81:79:32:80:3A:83:8D:80:F8`

> ⚠️ **Note:** The iOS version is currently in early access and has not been fully tested yet.

## App Features

- Browse popular, trending, or top-voted stations
- Automatic song recognition
- Save Favorite Radios
- Search Radios by name, genre, language, or country
- Display station metadata like country, website, language
- Dual Themes (Dark and Light)
- Play / Pause / Volume Control
- Audio Background Playback Support
- User-Friendly Interface with Easy Navigation

## Sponsor this Project

<p align="left">
  <a href="https://www.buymeacoffee.com/tawhidmonowar" target="_blank">
    <img src="https://img.buymeacoffee.com/button-api/?text=Buy me a coffee&emoji=☕&slug=tawhidmonowar&button_colour=FFDD00&font_colour=000000&font_family=Comic&outline_colour=000000&coffee_colour=ffffff" alt="Buy Me A Coffee" width="250" />
  </a>
</p>

## Screenshots (Android)
|![Home Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/1.png) | ![PLaying Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/2.png) | ![Search Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/3.png) |![Error Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/4.png) |
|:-------------------:|:------------------------:|:-----------------:|:-----------------:|

## Architecture Overview

This project follows the MVVM (Model-View-ViewModel) design pattern combined with Clean Architecture principles to ensure a scalable, maintainable, and testable structure.

![image](https://github.com/OneDroid/RadioWave/blob/main/readme/images/architecture-overview.jpg)

- The Presentation Layer is responsible for rendering the UI and managing user interactions via a unidirectional flow of Intent, Action, and ViewModel.
- The Domain Layer encapsulates business logic in UseCases, This layer is completely independent of other layers, ensuring that it can be reused and tested in isolation.
- The Data Layer abstracts data sources (API, Database) through a Repository pattern.


## Technologies and Libraries Used

This project is powered by a combination of awesome technologies and libraries, Below is a list of what’s used.

| Name                                      | Description                                                  |
|-------------------------------------------|--------------------------------------------------------------|
| [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) | Simplifies the development of cross-platform projects.       |
| [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform) | Builds responsive, modern user interfaces across multiple platforms. |
| [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) | Handles JSON and other formats with built-in serialization support. |
| [Material3](https://developer.android.com/jetpack/androidx/releases/compose-material3) | Provides Material Design 3 components and guidelines for UI design. |
| [Datastore Preferences](https://developer.android.com/topic/libraries/architecture/datastore) | A modern tool for storing key-value data locally.            |
| [Koin](https://insert-koin.io/)           | Manages dependencies in Kotlin apps with minimal effort.     |
| [Ktor Client](https://ktor.io/docs/getting-started-ktor-client.html) | Handles HTTP requests and networking in Kotlin.              |
| [Coil](https://coil-kt.github.io/coil)    | Loads images efficiently, designed specifically for Kotlin and Jetpack Compose. |
| [Media3 ExoPlayer](https://developer.android.com/media/media3/exoplayer) | Seamless audio and video playback for Android apps.          |
| [SQLite](https://developer.android.com/jetpack/androidx/releases/sqlite) | An embedded SQL database for structured data storage.         |
| [Radio Browser API](https://www.radio-browser.info)   | A free and open-source API to search and stream online radio stations. |

## Contribution Analytics
![analytics](https://repobeats.axiom.co/api/embed/0ed4b95566c02078f950078ddc20956855283d18.svg "RadioWave")

## Contributors  

We appreciate everyone who has contributed to this project! Your support and contributions help make **RadioWave** better every day.  

<a href="https://github.com/OneDroid/RadioWave/graphs/contributors">
  <img src="http://contrib.rocks/image?repo=OneDroid/RadioWave&max=100&columns=20" alt="Contributors" />
</a>
