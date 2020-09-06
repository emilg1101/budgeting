package com.github.emilg1101.budgeting.widget.balance.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTotalBalanceUseCase @Inject constructor() {

   private val balance = flow<Long> {
       var amount = 0L
       while (true) {
           amount+=1000
           emit(amount)
           delay(1000)
       }
   }

    operator fun invoke() = balance
}
