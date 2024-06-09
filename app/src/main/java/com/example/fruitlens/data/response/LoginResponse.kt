package com.example.fruitlens.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("logged")
	val logged: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
