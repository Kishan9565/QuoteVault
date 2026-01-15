package com.example.quotevault.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json

object SupabaseClient {

    private const val SUPABASE_URL =
        "https://oqedrosauxmghadmvwmk.supabase.co"

    private const val SUPABASE_ANON_KEY =
        "sb_publishable_oltIwg6UICrpJbZrj0sgnw_s1wxlCzD"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_ANON_KEY
    ) {

        install(Auth)

        install(Postgrest) {
            serializer = KotlinXSerializer(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}
