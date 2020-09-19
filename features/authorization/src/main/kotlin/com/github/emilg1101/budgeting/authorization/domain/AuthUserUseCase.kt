package com.github.emilg1101.budgeting.authorization.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.UserRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    operator fun invoke(idToken: String) = userRepository.auth(idToken).flowOn(ioDispatcher)
}
