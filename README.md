# 🔔 KMP Notifications Demo

A Kotlin Multiplatform demo showing how to request notification permissions and trigger **native local notifications** on both **Android** and **iOS** — with all the logic written in **Kotlin**.

This demo proves that KMP isn’t just for sharing ViewModels, you can even handle **notifications natively** using `expect/actual`.

---

## 🚀 Features

- ✅ Single Kotlin API for notifications
- 📱 Works on Android and iOS
- 🧩 Uses `expect/actual` to access native notification frameworks
- 🍏 Minimal Swift code — only an `AppDelegate` for iOS setup
- ⚡ Fully functional local notifications demo

---

## 🧠 How it works

The shared module defines an **expected** class:

```kotlin
expect class NotificationService {

  fun showNotification(
    title: String,
    message: String?
  )

  fun requestPermission(
    activity: PlatformActivity,
    onFinished: (Boolean) -> Unit
  )

  suspend fun areNotificationsEnabled(): Boolean
}
```

Each platform provides its **actual** implementation.

### Android
- Uses `NotificationCompat` to create and display notifications
- Handles Android 13+ permission requests
- Creates a simple notification channel

### iOS
- Uses `UNUserNotificationCenter` and `UIApplication` directly from Kotlin
- Requests permission and sends notifications through iOS APIs
- Only requires a tiny bit of Swift in `AppDelegate` for delegate registration

---

## 🧪 Demo UI

A minimal interface with two buttons:
- **Request Permission** → calls `askForNotificationPermission`
- **Show Notification** → triggers `showNotification()`
- -**ViewModel** to call the service and observe if notifications are enabled

You can build it using:
- **Compose Multiplatform** for a unified UI  
  or
- Native **SwiftUI / Android XML** — both work perfectly with this shared logic

---

## 🛠️ Requirements

- **Android Studio Ladybug+** or later
- **Xcode 15+**
- **Kotlin 2.0+**
- **Compose Multiplatform (optional)** if you’re using shared UI

---

## ▶️ Running the demo

### Android
Just hit **Run** from Android Studio and tap the buttons.

### iOS
Open the iOS app in Xcode once (to register the `AppDelegate`), then run:
```bash
./gradlew iosDeployIPhoneSimulator
```
or run it directly from Xcode.

Make sure to **allow notifications** when prompted.

---

## 💬 Example output

When you press **Show Notification**, you’ll see:
> “Hello, ${getPlatform().name}!”
> “This is a notification message.”

appearing natively on both platforms.

---

## 🧩 Why this matters

This project demonstrates how Kotlin Multiplatform lets you:
- Access **native platform APIs** directly from Kotlin
- Replace boilerplate Swift/Kotlin with a single shared implementation
- Keep full control while sharing real functionality

KMP is not just about shared logic — it’s about shared power ⚡

---

## 🏁 Credits

Built with ❤️ by **KMP Bits**  
Follow more Kotlin Multiplatform content on:
- **[KMP Bits](https://kmpbits.com)**  
- **[Medium](https://medium.com/@kmpbits)**  
