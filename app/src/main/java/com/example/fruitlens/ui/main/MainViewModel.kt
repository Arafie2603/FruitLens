package com.example.fruitlens.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fruitlens.data.pref.UserModel
import com.example.fruitlens.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getSession() : LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }
    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}