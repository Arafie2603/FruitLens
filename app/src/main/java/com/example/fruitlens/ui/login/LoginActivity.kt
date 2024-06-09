package com.example.fruitlens.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.fruitlens.ViewModelFactory
import com.example.fruitlens.data.pref.UserModel
import com.example.fruitlens.databinding.ActivityLoginBinding
import com.example.fruitlens.ui.main.MainActivity
import com.example.fruitlens.ui.signup.SignupActivity
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
        binding.textSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            true.showLoading()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Silakan isi data dulu", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
                viewModel.loginResponse.observe(this, Observer { response ->
                    Log.e("LoginActivity", "nama = ${response.email}")
                    false.showLoading()
                    val loginStatus = response.logged
                    if (loginStatus != false) {
                        val user = UserModel(response.email.toString(), response.token.toString(), true)
                        viewModel.saveSession(user)
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra(NAME, response.email)
                        startActivity(intent)
                        finish()
                    } else {
                        false.showLoading()
                        Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
                    }
                })
                viewModel.error.observe(this, Observer { error ->
                    Log.e("LoginActivity", "err = $error")
                })
            }
        }
    }

    private fun String.showToast() {
        Toast.makeText(this@LoginActivity, this, Toast.LENGTH_SHORT).show()
    }
    private fun Boolean.showLoading() {
        binding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }

    companion object {
        const val NAME = "name"
    }
}