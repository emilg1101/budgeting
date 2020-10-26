package com.github.emilg1101.budgeting.settings.domain

import com.github.emilg1101.budgeting.domain.BaseUseCase
import com.github.emilg1101.budgeting.domain.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    suspend operator fun invoke() = launch { userRepository.logout() }
}
