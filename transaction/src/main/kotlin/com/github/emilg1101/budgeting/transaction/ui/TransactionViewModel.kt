package com.github.emilg1101.budgeting.transaction.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.mapFlatten
import com.github.emilg1101.budgeting.domain.repository.CategoryRepository
import com.github.emilg1101.budgeting.transaction.ui.model.*
import com.github.emilg1101.budgeting.transaction.ui.picker.DateTimePickerCallback
import com.github.emilg1101.budgeting.transaction.widget.AmountChangeCallback
import java.util.*
import javax.inject.Inject

@FeatureScope
class TransactionViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel(), DateTimePickerCallback, AmountChangeCallback {

    private val _transactionDate = MutableLiveData<TransactionDate>(TransactionDate.Today())
    val transactionDate: LiveData<TransactionDate>
        get() = _transactionDate

    private val _transactionType = MutableLiveData<TransactionType>(TransactionType.Expense)
    val transactionType: LiveData<TransactionType>
        get() = _transactionType

    private val _transactionAmount = MutableLiveData(0L)
    val transactionAmount: LiveData<Long>
        get() = _transactionAmount

    private val _enrollmentCategories: LiveData<List<BaseCategory>> =
        _transactionType.switchMap<TransactionType, List<BaseCategory>> {
            when (it) {
                TransactionType.Expense -> categoryRepository.getCategories()
                    .mapFlatten(CategoryMapper)
                    .asLiveData()
                TransactionType.Income -> categoryRepository.getAccounts()
                    .mapFlatten(AccountMapper)
                    .asLiveData()
            }
        }
    val enrollmentCategories: LiveData<List<BaseCategory>>
        get() = _enrollmentCategories

    private val _withdrawCategories: LiveData<List<BaseCategory>> =
        _transactionType.switchMap<TransactionType, List<BaseCategory>> {
            when (it) {
                TransactionType.Expense -> categoryRepository.getAccounts()
                    .mapFlatten(AccountMapper)
                    .asLiveData()
                TransactionType.Income -> categoryRepository.getAccounts()
                    .mapFlatten(AccountMapper)
                    .asLiveData()
            }
        }
    val withdrawCategories: LiveData<List<BaseCategory>>
        get() = _withdrawCategories

    private val _selectedWithdrawCategory = MutableLiveData<BaseCategory>()
    val selectedWithdrawCategory: LiveData<BaseCategory>
        get() = _selectedWithdrawCategory

    private val _selectedEnrollmentCategory = MutableLiveData<BaseCategory>()
    val selectedEnrollmentCategory: LiveData<BaseCategory>
        get() = _selectedEnrollmentCategory

    fun toggleTransactionType() {
        if (_transactionType.value == TransactionType.Income) {
            _transactionType.value = TransactionType.Expense
        } else {
            _transactionType.value = TransactionType.Income
        }
        _selectedEnrollmentCategory.value = null
    }

    fun withdrawCategoryChange(category: BaseCategory) {
        _selectedWithdrawCategory.value = category
    }

    fun enrollmentCategoryChange(category: BaseCategory) {
        _selectedEnrollmentCategory.value = category
    }

    override fun onDateTimePicked(calendar: Calendar) {
        _transactionDate.value = TransactionDate.Day(calendar)
    }

    override fun onAmountChanged(amount: Float) {
        _transactionAmount.value = (amount * 100).toLong()
    }
}

sealed class TransactionDate {
    open val date: Calendar = Calendar.getInstance()

    class Today : TransactionDate() {
        override val date: Calendar
            get() = Calendar.getInstance()
    }

    class Day(override val date: Calendar) : TransactionDate()
}

sealed class TransactionType {
    object Income : TransactionType()
    object Expense : TransactionType()
}
