# 💬 ChatConnect

A real-time Android chat application built with Firebase for seamless real-time messaging and user authentication.

## 📱 About

ChatConnect is a modern Android application developed in Android Studio that demonstrates professional mobile development practices. It leverages Firebase for real-time data synchronization, secure authentication, and cloud storage. The app showcases clean architecture, MVVM pattern implementation, and integration with backend services.

## 🚀 Features

- Real-time messaging with Firebase Realtime Database
- User authentication (Email/Password, Google Sign-In)
- User profiles and online status tracking
- Push notifications for new messages
- Cloud storage for media and profile pictures
- Message history and persistence
- User search and friend management
- Responsive Material Design UI
- Offline message composition with sync

## 🛠 Tech Stack

- **Language:** Java
- **Platform:** Android (API Level 21+)
- **IDE:** Android Studio
- **Build System:** Gradle (Kotlin DSL)
- **Backend:** Firebase (Auth, Realtime DB, Storage, Cloud Messaging)
- **Architecture:** MVVM Pattern
- **UI:** Material Design
- **Additional:** LiveData, ViewModel, Glide, Retrofit

## 🏗 Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture for clean code separation:
- **Models:** Data classes for users, messages, and conversations
- **Views:** Activities and Fragments handling UI rendering
- **ViewModels:** Business logic and data management
- **Repositories:** Data source abstraction (Firebase, local DB)
- **Firebase:** Real-time backend services and authentication

This ensures maintainability, testability, scalability, and clear separation of concerns.

## 📦 Installation

**Prerequisites:**
- Android Studio (latest)
- JDK 11+
- Android SDK (API 21+)
- Firebase account

**Steps:**

1. Clone the repository
   ```bash
   git clone https://github.com/1DaddyCool1/chatConnect.git
   cd chatConnect
Open in Android Studio

Launch Android Studio
Select "Open an Existing Project"
Choose the chatConnect folder
Wait for Gradle sync
Configure Firebase

Go to Firebase Console
Create/select your project
Add Android app and download google-services.json
Place it in the app/ directory
Build and Run

bash
./gradlew build
Connect device or start emulator
Press Shift + F10 to run
🔐 Firebase Setup
Authentication: Configure email/password and Google Sign-In in Firebase Console.

Realtime Database Rules:

JSON
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "auth.uid === $uid",
        ".write": "auth.uid === $uid"
      }
    },
    "messages": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
Storage Rules:

Code
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /profile_pictures/{userId}/{allPaths=**} {
      allow read: if request.auth != null;
      allow write: if request.auth.uid == userId;
    }
  }
}
💡 Key Features
Real-time Messaging: Messages sync instantly across all devices using Firebase Realtime Database with ValueEventListener. User Authentication: Firebase handles secure registration and login without local password storage. Offline Support: Firebase caches data locally, enabling message viewing and composition without internet. Cloud Storage: Profile pictures and media stored securely in Firebase Storage with proper access control.

🧪 Testing
Run unit tests:

bash
./gradlew test
Run instrumented tests:

bash
./gradlew connectedAndroidTest
📚 Dependencies
Firebase SDK (Authentication, Database, Storage, Messaging)
AndroidX libraries (AppCompat, RecyclerView, Constraint Layout)
Material Design components
Lifecycle (ViewModel, LiveData)
Glide (Image loading)
Retrofit (HTTP client)
See app/build.gradle.kts for complete list.

🔮 Future Improvements
 Group chat functionality
 Voice and video calling
 End-to-end message encryption
 Message reactions and emoji support
 Typing indicators
 User blocking and reporting
 Message search
 Dark mode support
 Firebase Analytics and Crashlytics
 FCM push notifications enhancement
🚨 Troubleshooting
Firebase Issues: Verify google-services.json in app/ directory, check Firebase Console settings, ensure internet connectivity.

Gradle Errors: Update Android Studio, run ./gradlew clean, invalidate caches (File → Invalidate Caches).

Authentication Problems: Enable Firebase Authentication in Console, verify email/password settings, check user registration logic.

📝 Code Standards
Follow Android naming conventions (camelCase for variables/methods, PascalCase for classes)
Use meaningful names for variables and functions
Comment complex logic and Firebase integration
Maintain MVVM pattern consistency
Handle exceptions appropriately
Write reusable components
👨‍💻 Developer
GitHub: @1DaddyCool1

Skills: Android Development (Java) • Firebase Integration • Backend Development (Java, C#/.NET) • Clean Architecture • OOP

Status: Open to work 🚀 - Seeking junior-level Android/Backend positions

🤝 Contributing
Fork the repository
Create a feature branch: git checkout -b feature/YourFeature
Commit changes: git commit -m 'Add YourFeature'
Push to branch: git push origin feature/YourFeature
Open a Pull Request
📄 License
MIT License - Feel free to use, modify, and distribute for personal and commercial purposes.

📞 Support
Have questions or issues? Open an issue on GitHub with detailed description
