package com.example.fruitlens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fruitlens.data.repository.UserRepository
import com.example.fruitlens.ui.home.HomeViewModel
import com.example.fruitlens.ui.login.LoginViewModel
import com.example.fruitlens.ui.main.MainViewModel
import com.example.fruitlens.ui.signup.SignupViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val userRepository: () -> UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository()) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(userRepository()) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userRepository()) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository()) as T
            }
            else -> throw IllegalAccessException("Unkown ViewModel class : ${modelClass.name}")
        }
    }
}