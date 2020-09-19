package com.github.emilg1101.budgeting.transaction.ui.model

import com.github.emilg1101.budgeting.domain.entity.Account as DomainAccount
import com.github.emilg1101.budgeting.domain.entity.Category as DomainCategory
import com.github.emilg1101.budgeting.domain.entity.Income as DomainIncome

open class BaseCategory(open val id: Int, open val name: String) {
    var enabled = true
}

data class Category(override val id: Int, override val name: String, val emoji: String) :
    BaseCategory(id, name)

data class Account(override val id: Int, override val name: String, val amount: Long) :
    BaseCategory(id, name)

data class Income(override val id: Int, override val name: String) : BaseCategory(id, name)

val CategoryMapper = { category: DomainCategory ->
    Category(category.id, category.name, category.emoji)
}

val AccountMapper = { account: DomainAccount ->
    Account(account.id, account.name, account.amount)
}

val IncomeMapper = { income: DomainIncome ->
    Income(income.id, income.name)
}
