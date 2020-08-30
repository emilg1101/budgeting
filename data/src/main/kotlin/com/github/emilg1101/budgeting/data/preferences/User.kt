package com.github.emilg1101.budgeting.data.preferences

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id_token")
    val idToken: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("creation_timestamp")
    val creationTimestamp: Long,
    @SerializedName("last_login_timestamp")
    val lastLoginTimestamp: Long
)
