package com.example.fruitlens.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.fruitlens.ViewModelFactory
import com.example.fruitlens.databinding.ActivitySignupBinding
import com.example.fruitlens.ui.login.LoginActivity
import com.example.fruitlens.utils.Injection

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory { Injection.provideUserRepository(requireContext(this)) }
    }
    private fun requireContext(signupActivity: SignupActivity): Context {
        return signupActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupAction()
    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            val name = binding.inputNama.text.toString()
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            true.showLoading()
            viewModel.register(name, email, password)
            viewModel.registerReponse.observe(this, Observer {response->
                val registerStatus = response.id
                if (registerStatus.isNullOrEmpty()) {
                    "Registrasi gagal akun sudah terdaftar".showToast()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    "Registrasi berhasil".showToast()
                }
            })
        }
    }
    private fun String.showToast() {
        Toast.makeText(this@SignupActivity, this, Toast.LENGTH_SHORT).show()
    }
    private fun Boolean.showLoading() {
        binding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }
}