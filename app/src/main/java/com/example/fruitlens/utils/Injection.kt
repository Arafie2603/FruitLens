package com.example.fruitlens.utils

import android.content.Context
import com.example.fruitlens.data.api.ApiConfig
import com.example.fruitlens.data.pref.UserPreference
import com.example.fruitlens.data.pref.dataStore
import com.example.fruitlens.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context) : UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val authService = ApiConfig.getApiService()
        return UserRepository.getInstance(pref, authService)
    }
}