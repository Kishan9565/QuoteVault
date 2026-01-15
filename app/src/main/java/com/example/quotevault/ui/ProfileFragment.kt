package com.example.quotevault.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quotevault.R
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.quotevault.data.AuthRepository
import com.example.quotevault.databinding.FragmentProfileBinding
import com.example.quotevault.viewmodels.FavouriteViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val favouriteViewModel: FavouriteViewModel by activityViewModels()

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = requireContext().getSharedPreferences("app_prefs", 0)

        setupUserInfo()
        setupFavouriteCount()
        setupThemeSwitch()
        setupLogout()
    }


    private fun setupUserInfo() {
        val user = AuthRepository.getCurrentUser()
        binding.tvUserEmail.text = user?.email ?: "Unknown User"
    }


    private fun setupFavouriteCount() {
        favouriteViewModel.loadFavourites()

        viewLifecycleOwner.lifecycleScope.launch {
            favouriteViewModel.favourites.collect { favSet ->
                binding.tvFavouriteCount.text = favSet.size.toString()
            }
        }
    }


    private fun setupThemeSwitch() {
        val isDark = prefs.getBoolean("dark_mode", false)
        binding.switchTheme.isChecked = isDark

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {

            viewLifecycleOwner.lifecycleScope.launch {

                AuthRepository.signOut()

                val intent = Intent(
                    requireContext(),
                    AuthActivity::class.java
                )
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
