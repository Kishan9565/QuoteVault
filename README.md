# 📱 QuoteVault – Android Application

QuoteVault is a modern Android application developed as part of a Mobile Application Developer assignment.  
The application allows users to explore inspirational quotes across different categories, mark their favourite quotes, and manage their profile using secure authentication.

The project is built using **Kotlin**, follows the **MVVM architecture**, and uses **Supabase** as the backend for authentication and data storage.

---

## 🎯 Project Objectives

- Implement secure user authentication (Login / Logout)
- Fetch and display data from a backend service
- Enable category-wise filtering of quotes
- Allow users to save and manage favourite quotes
- Display user-related information in the profile section
- Follow clean architecture and Material Design principles

---

## 🛠 Tech Stack

- **Language:** Kotlin  
- **Architecture:** MVVM (Model–View–ViewModel)  
- **UI:** XML, Material Components  
- **Backend:** Supabase  
  - Authentication  
  - PostgreSQL Database  
- **Async Handling:** Kotlin Coroutines, Flow  
- **Serialization:** Kotlinx Serialization  

---

## ✨ Features

### 🔐 Authentication
- Secure login using Supabase Authentication
- Session-based login persistence
- Logout functionality from the profile screen

### 🏠 Home Screen
- Quote of the Day
- Horizontal category list
- Category-wise quote filtering
- Clean and responsive user interface

### ❤️ Favourite Quotes
- Add or remove quotes from favourites
- User-specific persistent storage
- Dedicated favourites screen
- Real-time updates using ViewModel and Flow

### 👤 Profile Screen
- Displays logged-in user email
- Shows total number of favourite quotes
- Logout functionality
- Scalable structure for future enhancements

---

## 🧩 Architecture Overview

The application follows the **MVVM (Model–View–ViewModel)** pattern to ensure scalability and maintainability.
View (Activity / Fragment)
↓
ViewModel (StateFlow)
↓
Repository
↓
Supabase (Auth + Database)
This separation improves scalability, testability, and maintainability.

---

## 📂 Project Structure

com.example.quotevault
│
├── data
│ ├── model
│ └── repository
│
├── ui
│ ├── home
│ ├── favourite
│ └── profile
│
├── viewmodel
│
└── utils


---

## 📸 Screenshots

All application screenshots are available in the `screenshots/` directory and include:
<img width="1920" height="1080" alt="Chemical Reactions and Energy Changes Education Presentation in Blue White Flat Cartoon Style" src="https://github.com/user-attachments/assets/511b55aa-1010-434e-af7c-d9a030a6fa79" />

---

## 🎥 Demo Video

**Loom Demo Video:**  
👉 * *

The video demonstrates:
- Application overview
- Navigation flow
- Favourite functionality
- Profile and logout
- Backend integration

---

## 🚀 Getting Started

### Prerequisites
- Android Studio
- Supabase account

### Installation

1. Clone the repository  
   ```bash
   git clone https://github.com/Kishan9565/QuoteVault.git
   
2. Open the project in Android Studio
3. Add Supabase credentials:
4. Supabase URL
5. Supabase Anon Key
6. Sync Gradle files
7. Run the application on an emulator or physical device

## 🧠 Key Learnings

Real-world implementation of MVVM architecture
Supabase authentication and database integration
State management using Kotlin Flow
Secure handling of user-specific data
Building scalable and maintainable Android applications
