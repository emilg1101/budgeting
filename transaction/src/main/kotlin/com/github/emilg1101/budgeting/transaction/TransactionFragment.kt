package com.github.emilg1101.budgeting.transaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.transaction.di.DaggerTransactionComponent
import kotlinx.android.synthetic.main.fragment_transaction.*
import javax.inject.Inject

class TransactionFragment : NestedFragment<TransactionViewModel>(R.layout.fragment_transaction) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: TransactionViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerTransactionComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionClose.setOnClickListener { findNavController().navigateUp() }
    }
}
