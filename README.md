# Transport Cost Comparator (Android)

An Android application built with Java and SQLite that calculates and compares daily and monthly transport costs across different modes of transport. This project demonstrates mobile UI design, DatePicker integration, audio playback, and local data persistence.

## Project Overview

This app helps users calculate their daily and monthly transport costs based on their mode of transport, distance travelled, cost per kilometre, and number of travel days. Users select travel days via a DatePicker, and the app stores all reports locally using SQLite for future reference. The app also features a splash screen, Action Bar navigation, and audio playback for an enhanced user experience.

## Tech Stack

- **Language:** Java
- **Platform:** Android Studio
- **Database:** SQLite (local storage)
- **UI:** XML Layouts, Action Bar, DatePicker
- **Media:** Android MediaPlayer (audio playback)
- **IDE:** Android Studio

## Key Features

- **Transport Mode Selection:** Choose between different modes of transport.
- **Cost Calculation:** Calculates daily and monthly costs based on distance, cost per kilometre, and travel days.
- **Date Selection:** Uses a DatePicker to let users select travel days.
- **Persistent Storage:** Saves all reports locally using SQLite.
- **Splash Screen:** Professional launch experience with a branded splash screen.
- **Action Bar Navigation:** Easy navigation between screens.
- **Audio Playback:** Includes audio feedback for an enhanced user experience.
- **Report History:** View past reports and track spending over time.

## Architecture & Code Structure

| Component                       | Role                                                                                                                                        |
| ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| `MainActivity.java`             | **UI Controller:** Handles user input, triggers calculations, and displays results.                                                         |
| `DatabaseHelper.java`           | **Data Access:** Manages SQLite database creation and CRUD operations.                                                                      |
| `TransportReport.java`          | **Model:** Represents a transport cost report with encapsulated fields.                                                                     |      
| `SplashActivity.java`           | **Entry Point:** Displays the splash screen before launching the main activity.                                                             |
| `activity_main.xml`             | **View:** Defines the main UI components (EditTexts, Spinner, DatePicker, Buttons).                                                         |



## Engineering Highlights

- **Local Data Persistence:** Used SQLite for offline storage, ensuring the app works without an internet connection.
- **Date Handling:** Integrated Android's DatePicker to allow users to select travel days efficiently.
- **Media Integration:** Implemented audio playback using Android's MediaPlayer API.
- **Input Validation:** Ensures distance, cost per kilometre, and travel days are valid positive numbers.
- **Multi-Screen Navigation:** Used Action Bar and Intents to navigate between activities cleanly.

## What I Learned

- Building Android applications with Java in Android Studio.
- Working with SQLite for local data storage.
- Integrating Android UI components (DatePicker, Spinner, Action Bar).
- Implementing a splash screen and multi-activity navigation.
- Using Android's MediaPlayer for audio playback.
- Handling user input and form validation in Android.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/GRJuniorselemela-se/transport-cost-comparator.git
   ```
2. Open the project in Android Studio.
3. Let Gradle sync complete.
4. Connect an Android device or start an emulator.
5. Run the app.

## Future Improvements

- Charts and Graphs: Add visual charts comparing transport costs over time.
- Map Integration: Use Google Maps API to calculate distances automatically.
- Cloud Backup: Sync data to Firebase for cross-device access.
- Multiple Vehicle Profiles: Support different vehicle types with pre-set fuel costs.
- Export Reports: Allow users to export reports as PDF or CSV.

## Academic Context

- Developed during Mobile Application Development and Big Data (ITMBA2) as part of a second-year Software Engineering curriculum.

## Author

**Gomolemo Reggy Junior Selemela**

Software Engineering Student | Aspiring Software Developer/ Software Engineer (Java)
- **GitHub:** [github.com/GRJuniorselemela-se](https://github.com/GRJuniorselemela-se)
- **LinkedIn:** [linkedin.com/in/grjunior-selemela](https://www.linkedin.com/in/grjunior-selemela)
- **Email:** [juniorselemela@gmail.com](mailto:juniorselemela@gmail.com)


