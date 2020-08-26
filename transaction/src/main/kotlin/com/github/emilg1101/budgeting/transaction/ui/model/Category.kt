package com.github.emilg1101.budgeting.transaction.ui.model

import com.github.emilg1101.budgeting.domain.entity.Account as DomainAccount
import com.github.emilg1101.budgeting.domain.entity.Category as DomainCategory

open class BaseCategory(open val name: String) {
    var enabled = true
}

data class Category(override val name: String, val emoji: String) : BaseCategory(name)

data class Account(override val name: String, val amount: Long) : BaseCategory(name)

val CategoryMapper = { category: DomainCategory ->
    Category(category.name, category.emoji)
}

val AccountMapper = { account: DomainAccount ->
    Account(account.name, account.amount)
}
