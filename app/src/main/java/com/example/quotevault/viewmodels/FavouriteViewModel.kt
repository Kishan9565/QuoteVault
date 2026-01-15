package com.example.quotevault.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotevault.data.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    private val _favourites = MutableStateFlow<Set<Int>>(emptySet())
    val favourites: StateFlow<Set<Int>> = _favourites

    fun loadFavourites() {
        viewModelScope.launch {
            val favs = FavouriteRepository.getFavourites()
            _favourites.value = favs.map { it.quote_id }.toSet()
        }
    }


    fun toggleFavourite(quoteId: Int) {
        viewModelScope.launch {
            if (_favourites.value.contains(quoteId)) {
                FavouriteRepository.removeFavourite(quoteId)
            } else {
                FavouriteRepository.addFavourite(quoteId)
            }
            loadFavourites()
        }
    }
}
