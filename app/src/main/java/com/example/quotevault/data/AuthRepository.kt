package com.example.quotevault.data

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

object AuthRepository {

    suspend fun signUp(email: String, password: String) {
        SupabaseClient.client.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signIn(email: String, password: String) {
        SupabaseClient.client.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

     suspend fun signOut() {
        SupabaseClient.client.auth.signOut()
    }

    fun getCurrentUser() =
        SupabaseClient.client.auth.currentUserOrNull()


    fun isLoggedIn(): Boolean {
        return SupabaseClient.client.auth.currentSessionOrNull() != null
    }
}
