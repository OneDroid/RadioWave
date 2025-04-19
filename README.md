## RadioWave
RadioWave is a cross-platform internet radio player for Android and iOS, built with Kotlin Multiplatform. It provides access to over 50,000 stations worldwide with automatic song recognition that identifies what's playing in real time.

![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/OneDroid/RadioWave/total)
![visitors](https://visitor-badge.laobi.icu/badge?page_id=OneDroid.RadioWave)
[![SavePalestine](https://raw.githubusercontent.com/OneDroid/.github/refs/heads/main/images/badge/save-palestine.svg)](https://www.youtube.com/watch?v=O5fbyEV36pU)

## Download
[<img src="https://raw.githubusercontent.com/OneDroid/.github/220b8baeb925e81df5a3757fa878432bfb645b8c/images/badge/badge-github.svg" alt="" height="60">](https://github.com/OneDroid/RadioWave/releases)
[<img src="https://raw.githubusercontent.com/OneDroid/.github/220b8baeb925e81df5a3757fa878432bfb645b8c/images/badge/badge-obtainium.svg" alt="" height="60">](https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/OneDroid/RadioWave)

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

## Screenshots (Android)
|![Home Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/1.png) | ![PLaying Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/2.png) | ![Search Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/3.png) |![Error Screen](https://github.com/OneDroid/RadioWave/blob/main/readme/images/4.png) |
|:-------------------:|:------------------------:|:-----------------:|:-----------------:|

## Architecture Overview

This project follows the MVVM (Model-View-ViewModel) design pattern combined with Clean Architecture principles to ensure a scalable, maintainable, and testable structure.

![image](https://github.com/OneDroid/RadioWave/blob/main/readme/images/architecture-overview.jpg)

- The Presentation Layer is responsible for rendering the UI and managing user interactions via a unidirectional flow of Intent, Action, and ViewModel.
- The Domain Layer encapsulates business logic in UseCases, This layer is completely independent of other layers, ensuring that it can be reused and tested in isolation.
- The Data Layer abstracts data sources (API, Database) through a Repository pattern.

## Contribution analytics
![analytics](https://repobeats.axiom.co/api/embed/0ed4b95566c02078f950078ddc20956855283d18.svg "RadioWave")

## Contributors  

We appreciate everyone who has contributed to this project! Your support and contributions help make **RadioWave** better every day.  

<a href="https://github.com/OneDroid/RadioWave/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=OneDroid/RadioWave&max=100&columns=20" alt="Contributors" />
</a>
