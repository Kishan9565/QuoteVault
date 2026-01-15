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

