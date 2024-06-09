package com.example.fruitlens.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitlens.data.repository.UserRepository
import com.example.fruitlens.data.response.RegisterResponse
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerReponse: LiveData<RegisterResponse> get() = _registerResponse
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.register(name, email, password)
                _registerResponse.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}