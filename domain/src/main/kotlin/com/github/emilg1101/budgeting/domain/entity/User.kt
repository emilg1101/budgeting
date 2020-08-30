package com.github.emilg1101.budgeting.domain.entity

data class User(
    val idToken: String,
    val name: String,
    val image: String,
    val creationTimestamp: Long,
    val lastLoginTimestamp: Long
) {
    val isNewUser
        get() = creationTimestamp == lastLoginTimestamp
}
