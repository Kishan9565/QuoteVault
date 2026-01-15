package com.example.quotevault.data

import android.util.Log
import com.example.quotevault.model.Category
import com.example.quotevault.model.Quote
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object QuoteRepository {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun getCategories(): List<Category> {
        val response = SupabaseClient.client
            .from("categories")
            .select()
            .data

        Log.d("DATA_CHECK", "RAW JSON categories = $response")

        return json.decodeFromString(response)
    }

    suspend fun getAllQuotes(): List<Quote> {
        val response = SupabaseClient.client
            .from("quotes")
            .select()
            .data

        Log.d("DATA_CHECK", "RAW JSON quotes = $response")

        return json.decodeFromString(response)
    }

    suspend fun getQuotesByCategory(categoryId: Int): List<Quote> {
        val response = SupabaseClient.client
            .from("quotes")
            .select {
                filter {
                    eq("category_id", categoryId)
                }
            }
            .data

        return json.decodeFromString(response)
    }

    suspend fun getRandomQuote(): Quote? {
        return getAllQuotes().randomOrNull()
    }
}
