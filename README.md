# 📝 Notes Storage App

A simple **Android Notes Application** that allows users to create, view, and delete notes.
This project demonstrates how to use **two types of data storage** — SharedPreferences and local File Storage — with a clean and easy-to-use interface.

## 📱 Features

- Add, view, and delete notes.
- Switch between SharedPreferences and File Storage.
- Persistent note saving.
- Simple and clean interface.

## 🧠 Project Structure

```
app/
├── java/com/example/notesstorageapp/
│   ├── AddNoteActivity.java
│   ├── DeleteNoteActivity.java
│   ├── MainActivity.java
│   ├── NoteDetailActivity.java
│   ├── Note.java
│   ├── IStorage.java
│   ├── FileStorage.java
│   ├── SharedPreferencesStorage.java
│   ├── StorageManager.java
│   └── Constants.java
│
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_add_note.xml
│   │   ├── activity_delete_note.xml
│   │   └── activity_note_detail.xml
│   ├── menu/
│   │   └── menu_main.xml
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│
└── AndroidManifest.xml
```

## 🛠️ Tools & Technologies

- Java
- Android Studio
- SharedPreferences & File Storage
- Material Design Components

## 🚀 How to Run

1. Open the project in **Android Studio**.
2. Sync Gradle and build the project.
3. Run the app on an emulator or physical device.

## 🧑‍💻 Author

**Mubaraq Yusuf**
