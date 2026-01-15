package com.example.quotevault.data

import com.example.quotevault.model.Favourite
import com.example.quotevault.model.Quote
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from

object FavouriteRepository {

    private val client = SupabaseClient.client

    suspend fun getFavourites(): List<Favourite> {
        return client
            .from("favourites")
            .select()
            .decodeList()
    }

    suspend fun addFavourite(quoteId: Int) {
        val userId = client.auth.currentUserOrNull()?.id ?: return

        val fav = Favourite(
            user_id = userId,
            quote_id = quoteId
        )

        client
            .from("favourites")
            .insert(fav)
    }


    suspend fun removeFavourite(quoteId: Int) {
        val userId = client.auth.currentUserOrNull()?.id ?: return

        client.from("favourites").delete {
            filter {
                eq("user_id", userId)
                eq("quote_id", quoteId)
            }
        }
    }
}
