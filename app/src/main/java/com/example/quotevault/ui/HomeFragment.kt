package com.example.quotevault.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quotevault.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotevault.adapter.CategoryAdapter
import com.example.quotevault.adapter.QuoteAdapter
import com.example.quotevault.databinding.FragmentHomeBinding
import com.example.quotevault.viewmodels.FavouriteViewModel
import com.example.quotevault.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val favViewModel: FavouriteViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeViewModel()

        viewModel.loadCategories()
        viewModel.loadAllQuotes()
        viewModel.loadDailyQuote()
        favViewModel.loadFavourites()
    }

    private fun setupRecyclerViews() {

        categoryAdapter = CategoryAdapter {
            viewModel.loadQuotesByCategory(it.id)
        }

        binding.rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoryAdapter

        quoteAdapter = QuoteAdapter(emptySet()) {
            favViewModel.toggleFavourite(it)
        }

        binding.rvQuotes.layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvQuotes.adapter = quoteAdapter
    }

    private fun observeViewModel() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categories.collect {
                categoryAdapter.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quotes.collect {
                quoteAdapter.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.favourites.collect {
                quoteAdapter.updateFavourites(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dailyQuote.collect {
                it?.let { q ->
                    binding.tvDailyQuote.text = q.text
                    binding.tvDailyAuthor.text = "- ${q.author}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
