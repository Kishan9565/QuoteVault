package com.example.quotevault.model

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val text: String,
    val author: String,
    val category_id: Int
)
