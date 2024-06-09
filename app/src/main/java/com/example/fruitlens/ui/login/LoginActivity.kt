package com.example.fruitlens.ui.login

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.fruitlens.ViewModelFactory
import com.example.fruitlens.databinding.ActivityLoginBinding
import com.example.fruitlens.utils.Injection

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>() {
        ViewModelFactory { Injection.provideUserRepository(this) }
    }
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            viewModel.login(email, password)
            viewModel.loginResponse.observe(this, Observer {response->
                Log.e("LoginActivity", "nama = ${response.nama}")
            })
            viewModel.error.observe(this, Observer{error->
                Log.e("LoginActivity", "err = $error")
            })
        }
    }
}