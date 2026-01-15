📱 QuoteVault – Android Application
📌 Overview

QuoteVault is a modern Android application developed as part of a Mobile Application Developer Assignment.
The app allows users to explore inspirational quotes across multiple categories, mark their favourite quotes, and manage their profile.
It is built using Kotlin, MVVM architecture, and Supabase as the backend.

The project focuses on clean architecture, real-time backend integration, and a smooth user experience.

🎯 Assignment Objectives Covered

This project fulfills the following assignment requirements:

User Authentication (Login / Logout)

Fetching and displaying data from a backend

Category-wise content filtering

Favourite functionality with persistent storage

Profile screen with user-related information

Clean UI with Material Design principles

Proper GitHub repository with documentation

Demo video explaining the app flow

🛠 Tech Stack

Language: Kotlin

Architecture: MVVM (Model–View–ViewModel)

UI: XML, Material Components

Backend: Supabase

Authentication

PostgreSQL Database

Networking & Serialization:

Supabase Kotlin SDK

Kotlinx Serialization

Async Handling: Kotlin Coroutines & Flow

✨ Features
🔐 Authentication

Secure user login using Supabase Authentication

Session-based login persistence

Logout functionality from Profile screen

🏠 Home Screen

“Quote of the Day” section

Horizontal list of quote categories

Vertical list of quotes

Category-based quote filtering

Clean and responsive UI

❤️ Favourite Quotes

Add or remove quotes from favourites

Favourites stored securely per user

Dedicated Favourite screen

Real-time update using ViewModel & Flow

👤 Profile Screen

Displays logged-in user email

Shows total number of favourite quotes

Logout functionality

Ready structure for future enhancements (theme, profile image, etc.)

🧩 App Architecture (MVVM)

Model:
Data classes (Quote, Category, Favourite)

View:
Activities & Fragments (Home, Favourite, Profile)

ViewModel:
Handles business logic and exposes UI state using StateFlow

Repository:
Manages Supabase API calls and data operations

This separation ensures better scalability, testability, and maintainability.

📸 Screenshots
