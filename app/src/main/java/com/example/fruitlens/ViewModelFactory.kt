package com.example.fruitlens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fruitlens.data.repository.UserRepository
import com.example.fruitlens.ui.login.LoginViewModel

class ViewModelFactory (
    private val userRepository: () -> UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository()) as T
            }
            else -> throw IllegalAccessException("Unkown ViewModel class : ${modelClass.name}")
        }
    }
}