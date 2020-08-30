package com.github.emilg1101.budgeting.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    suspend fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        sharedPreferences.edit().putString(USER_DATA, userJson).apply()
    }

    suspend fun getUser(): User? {
        val userJson = sharedPreferences.getString(USER_DATA, null)
        return gson.fromJson(userJson, User::class.java)
    }

    suspend fun removeUser() {
        sharedPreferences.edit().remove(USER_DATA).apply()
    }

    companion object {
        private const val USER_DATA = "user_data"
    }
}
