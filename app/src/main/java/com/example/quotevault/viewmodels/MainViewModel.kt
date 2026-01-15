package com.example.quotevault.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotevault.data.QuoteRepository
import com.example.quotevault.model.Category
import com.example.quotevault.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes

    private val _dailyQuote = MutableStateFlow<Quote?>(null)
    val dailyQuote: StateFlow<Quote?> = _dailyQuote

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val result = QuoteRepository.getCategories()
                android.util.Log.d("DATA_CHECK", "ViewModel → categories size = ${result.size}")
                _categories.value = result
            } catch (e: Exception) {
                android.util.Log.e("DATA_CHECK", "ViewModel → loadCategories error", e)
            }
        }
    }


    fun loadAllQuotes() {
        viewModelScope.launch {
            try {
                val result = QuoteRepository.getAllQuotes()
                android.util.Log.d("DATA_CHECK", "ViewModel → quotes size = ${result.size}")
                _quotes.value = result
            } catch (e: Exception) {
                android.util.Log.e("DATA_CHECK", "ViewModel → loadAllQuotes error", e)
            }
        }
    }


    fun loadQuotesByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                val result = QuoteRepository.getQuotesByCategory(categoryId)
                android.util.Log.d(
                    "DATA_CHECK",
                    "ViewModel → quotes for category $categoryId = ${result.size}"
                )
                _quotes.value = result
            } catch (e: Exception) {
                android.util.Log.e("DATA_CHECK", "ViewModel → loadQuotesByCategory error", e)
            }
        }
    }


    fun loadDailyQuote() {
        viewModelScope.launch {
            try {
                val result = QuoteRepository.getRandomQuote()
                android.util.Log.d("DATA_CHECK", "ViewModel → daily quote = $result")
                _dailyQuote.value = result
            } catch (e: Exception) {
                android.util.Log.e("DATA_CHECK", "ViewModel → loadDailyQuote error", e)
            }
        }
    }

}
