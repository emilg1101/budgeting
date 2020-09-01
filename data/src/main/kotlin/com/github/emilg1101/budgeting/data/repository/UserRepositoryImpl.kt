package com.github.emilg1101.budgeting.data.repository

import com.github.emilg1101.budgeting.data.preferences.UserPreferences
import com.github.emilg1101.budgeting.domain.entity.User
import com.github.emilg1101.budgeting.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.github.emilg1101.budgeting.data.preferences.User as UserEntity

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userPreferences: UserPreferences
) : UserRepository {

    override suspend fun getUser(): User {
        return userPreferences.getUser()?.let { user ->
            User(
                user.idToken,
                user.name,
                user.image,
                user.creationTimestamp,
                user.lastLoginTimestamp
            )
        } ?: throw IllegalArgumentException()
    }

    override fun auth(idToken: String): Flow<User> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return flow {
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(result)
        }.map {
            val profile = it.user?.providerData?.get(0)
            User(
                idToken,
                profile?.displayName ?: "",
                profile?.photoUrl.toString(),
                it.user?.metadata?.creationTimestamp ?: 0L,
                it.user?.metadata?.lastSignInTimestamp ?: 0L
            )
        }.onEach {
            userPreferences.saveUser(
                UserEntity(
                    it.idToken,
                    it.name,
                    it.image,
                    it.creationTimestamp,
                    it.lastLoginTimestamp
                )
            )
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
        userPreferences.removeUser()
    }

    override suspend fun hasLoggedInUser(): Boolean {
        return userPreferences.getUser() != null
    }
}
