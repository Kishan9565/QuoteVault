package com.example.quotevault.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotevault.R
import com.example.quotevault.adapter.QuoteAdapter
import com.example.quotevault.databinding.FragmentFavouriteBinding
import com.example.quotevault.viewmodels.FavouriteViewModel
import com.example.quotevault.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var binding: FragmentFavouriteBinding

    private val mainViewModel: MainViewModel by activityViewModels()
    private val favViewModel: FavouriteViewModel by activityViewModels()

    private lateinit var adapter: QuoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouriteBinding.bind(view)

        adapter = QuoteAdapter(emptySet()) { }

        binding.rvFavQuotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavQuotes.adapter = adapter

        observeData()

        favViewModel.loadFavourites()
        mainViewModel.loadAllQuotes()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.favourites.collect { favIds ->
                val favQuotes = mainViewModel.quotes.value.filter {
                    favIds.contains(it.id)
                }
                adapter.submitList(favQuotes)
            }
        }
    }
}
