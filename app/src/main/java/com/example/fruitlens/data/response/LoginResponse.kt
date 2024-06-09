package com.example.fruitlens.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("nim")
	val nim: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("jabatan")
	val jabatan: String? = null
)
