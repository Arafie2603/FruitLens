package com.example.fruitlens.data.api

import com.example.fruitlens.data.response.LoginResponse
import com.example.fruitlens.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("users")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("users/login")
     suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse
}