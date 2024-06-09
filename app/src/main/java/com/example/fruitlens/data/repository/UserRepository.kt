package com.example.fruitlens.data.repository

import com.example.fruitlens.data.api.AuthService
import com.example.fruitlens.data.pref.UserModel
import com.example.fruitlens.data.pref.UserPreference
import com.example.fruitlens.data.response.LoginResponse
import com.example.fruitlens.data.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val authService: AuthService
){
     suspend fun login(email: String, password: String) : LoginResponse {
         return authService.login(email, password)
    }

    suspend fun register(name: String, email: String, password: String) : RegisterResponse {
        return authService.register(name, email, password)
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession() : Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }
    companion object {
        fun getInstance(
            userPreference: UserPreference,
            authService: AuthService,
        ) = UserRepository(userPreference, authService)
    }
}