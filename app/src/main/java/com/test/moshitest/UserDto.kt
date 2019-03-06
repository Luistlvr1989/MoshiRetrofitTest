package com.test.moshitest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "user_id") var id: Long,
    @Json(name = "email") var email: String,
    @Json(name = "token") var token: String,
    @Json(name = "is_validated") var isValidated: Boolean
)