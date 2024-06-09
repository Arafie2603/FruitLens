package com.example.fruitlens.utils

import android.content.Context
import com.example.fruitlens.data.api.ApiConfig
import com.example.fruitlens.data.pref.UserPreference
import com.example.fruitlens.data.pref.dataStore
import com.example.fruitlens.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context) : UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val authService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, authService)
    }
}