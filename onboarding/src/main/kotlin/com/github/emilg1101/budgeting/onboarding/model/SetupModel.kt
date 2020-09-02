package com.github.emilg1101.budgeting.onboarding.model

data class SetupModel(
    var categories: List<CategoryModel> = emptyList(),
    var accounts: List<CategoryModel> = emptyList()
)
