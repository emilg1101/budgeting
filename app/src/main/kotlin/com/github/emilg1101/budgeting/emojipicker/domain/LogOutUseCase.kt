package com.github.emilg1101.budgeting.emojipicker.domain

import com.github.emilg1101.budgeting.domain.repository.UserRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() = userRepository.logout()
}
