package com.example.quotevault.ui

import android.content.Intent
import io.github.jan.supabase.gotrue.auth
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.quotevault.R
import com.example.quotevault.data.AuthRepository
import com.example.quotevault.databinding.ActivityAuthBinding
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AuthRepository.isLoggedIn()) {
            goToHome()
            return
        }


        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        setupButton()
    }

    private fun setupTabs() {
        binding.tabLogin.setOnClickListener {
            isLoginMode = true

            binding.tabLogin.setBackgroundResource(android.R.color.white)
            binding.tabSignup.background = null

            binding.tabLogin.setTextColor(getColor(R.color.primary))
            binding.tabSignup.setTextColor(getColor(android.R.color.darker_gray))

            binding.tvSubtitle.text = "Sign in to continue"
        }

        binding.tabSignup.setOnClickListener {
            isLoginMode = false

            binding.tabSignup.setBackgroundResource(android.R.color.white)
            binding.tabLogin.background = null

            binding.tabSignup.setTextColor(getColor(R.color.primary))
            binding.tabLogin.setTextColor(getColor(android.R.color.darker_gray))

            binding.tvSubtitle.text = "Create a new account"
        }
    }

    private fun setupButton() {
        binding.btnAction.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    if (isLoginMode) {
                        AuthRepository.signIn(email, password)
                    } else {
                        AuthRepository.signUp(email, password)
                    }

                    Toast.makeText(this@AuthActivity, "Success", Toast.LENGTH_SHORT).show()
                    goToHome()

                } catch (e: Exception) {
                    Toast.makeText(
                        this@AuthActivity,
                        e.message ?: "Authentication failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HostActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}
