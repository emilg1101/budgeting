package com.github.emilg1101.budgeting.app.domain

import com.github.emilg1101.budgeting.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.hasLoggedInUser()
}
