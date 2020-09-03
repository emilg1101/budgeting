package com.github.emilg1101.budgeting.authorization.domain

import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.domain.repository.UserRepository
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(idToken: String) = userRepository.auth(idToken)
}
