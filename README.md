
---

# 🌲 Trails & Trials
Welcome to **Trails & Trials**, an Android application designed to enhance your hiking experience by providing popular trails, setting personal fitness goals, and even uncovering a hidden endless runner mini-game! This app, built with Kotlin, integrates Firebase, Google Maps, and an exciting custom game to keep users engaged and motivated.

---

## 📋 Table of Contents
- [✨ Features](#-features)
- [🛠 Installation](#-installation)
- [📝 Usage Guide](#-usage-guide)
- [🎮 Gameplay Instructions](#-gameplay-instructions)
- [💻 Developer Guide](#-developer-guide)
- [📦 Dependencies](#-dependencies)
- [🌍 Contribution](#-contribution)

---

## ✨ Features
- **Explore Hiking Trails** - View popular hiking trails with route details on an integrated Google Map.
- **Custom Route Creation** - Use Google Maps to create personalized routes for a tailored hiking experience.
- **Set Personal Goals** - Track your progress by setting daily step goals and viewing hike summaries.
- **Offline Mode** - Access trails and goals without a network connection.
- **Language Selection** - Choose your preferred app interface language.
- **Hidden Mini-Game** - Discover *Hike From Home*, an endless runner game hidden in the app.
- **Firebase Integration** - Sync data with Firebase for authentication, notifications, and persistent storage.

---

## 🛠 Installation

### Prerequisites
- **Android Studio** 4.0 or later
- **Android SDK** with minimum API level 21
- **Firebase account** with a configured project

### Steps
1. **Clone the Repository**

    ```bash
    git clone https://github.com/ST10158423/OPSC-PART-3.git
    cd OPSC-PART-3
    ```

2. **Open in Android Studio**  
   - Launch Android Studio and go to `File > Open`.
   - Select the project folder.

3. **Configure Firebase**  
   - Add `google-services.json` to the `/app` directory.
   - Enable **Firebase Authentication**, **Firestore**, and **Cloud Messaging** in your Firebase project.

4. **Build and Run**  
   - Connect an Android device or use an emulator.
   - Click **Run ▶️** to build and install the app.

---

## 📝 Usage Guide

### 1. Explore Hiking Trails
- Open the app and select a region from the main screen to view available hiking trails.
- Access route details via the integrated Google Map.

### 2. Create Your Own Route
- Go to the **Route Details** screen.
- Use Google Maps to create and save a custom route (requires developer mode for API access).

### 3. Set Your Step Goal
- Navigate to **Settings** to set a daily step goal.
- Monitor progress through hike summaries.

### 4. Enable Offline Mode
- Toggle the **Offline Mode** switch in **Settings** to access trails and goals without internet.

### 5. Change Language
- Select your preferred language from the **Language Selection** dropdown in Settings.

### 6. Hidden Mini-Game
- Tap five times on the background of the main area selection screen to unlock *Hike From Home*.

---

## 🎮 Gameplay Instructions

### Accessing the Game
- Tap five times in the blank space of the main Area Selection screen to launch the game.

### Playing *Hike From Home*
- The hiker automatically moves forward.
- **Tap the screen** to make the hiker jump over obstacles.
- **Goal**: Score as many points as possible before hitting an obstacle.
- **Restart**: Hitting a rock resets the game and score.

---

## 💻 Developer Guide

### Adding a New Trail (Developer Access Only)
- Open the **API** and add the new trail route.

### Modifying the Game Mechanics
- **Jump Speed**: Adjust `jumpSpeed` in `HikeFromHomeGameView.kt`.
- **Obstacle Movement**: Change the obstacle speed to increase difficulty.

### Updating the App Icon and Splash Screen
- Replace images in `res/mipmap` for the app icon.
- Update the splash screen image in `res/drawable`.

---

## 📦 Dependencies

```groovy
// build.gradle
dependencies {
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.firebase:firebase-messaging'
    implementation 'com.google.android.gms:play-services-maps'
    implementation 'org.jetbrains.kotlin:kotlin-stdlib'
}
```

---

## 🌍 Contribution

Contributions are welcome! Follow these steps:

1. **Fork** the repository.
2. **Create a feature branch**  
   ```bash
   git checkout -b feature/YourFeature
   ```
3. **Commit your changes**  
   ```bash
   git commit -m 'Add YourFeature'
   ```
4. **Push to the branch**  
   ```bash
   git push origin feature/YourFeature
   ```
5. **Open a Pull Request**

---

Enjoy your journey with Trails & Trials! 🌄

---
