package com.example.quotevault.model

import kotlinx.serialization.Serializable

@Serializable
data class Favourite(
    val id: String? = null,
    val user_id: String,
    val quote_id: Int
)

