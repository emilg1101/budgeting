package com.github.emilg1101.budgeting.home.domain

import com.github.emilg1101.budgeting.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() = userRepository.getUser()
}
