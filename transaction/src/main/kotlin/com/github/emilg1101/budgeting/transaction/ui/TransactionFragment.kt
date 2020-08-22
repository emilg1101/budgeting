package com.github.emilg1101.budgeting.transaction.ui

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.dpToPx
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.transaction.R
import com.github.emilg1101.budgeting.transaction.di.DaggerTransactionComponent
import com.github.emilg1101.budgeting.transaction.ui.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.transaction.ui.model.Account
import com.github.emilg1101.budgeting.transaction.ui.model.Category
import com.github.emilg1101.budgeting.transaction.widget.SpacesItemDecoration
import com.github.emilg1101.budgeting.transaction.widget.setUpWithTextView
import kotlinx.android.synthetic.main.fragment_transaction.*
import javax.inject.Inject
import javax.inject.Named

class TransactionFragment :
    NestedFragment<TransactionViewModel>(R.layout.fragment_transaction) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    @Named("Enrollment")
    lateinit var enrollmentAdapter: CategoryAdapter

    @Inject
    @Named("Withdraw")
    lateinit var withdrawAdapter: CategoryAdapter

    override val viewModel: TransactionViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerTransactionComponent.builder().coreComponent(this.coreComponent()).build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionCategoriesList.addItemDecoration(SpacesItemDecoration(context.dpToPx(10)))
        transactionContainer.getTransition(R.id.keyboardDown).setEnable(false)
        transactionClose.setOnClickListener {
            findNavController().navigateUp()
        }
        transactionEnrollmentToIcon.setOnClickListener {
            transactionContainer.transitionToEnd()
            transactionContainer.getTransition(R.id.keyboardDown).setEnable(true)
            transactionCategoriesList.adapter = enrollmentAdapter
            enrollmentAdapter.items = listOf(
                Category("Grocery Store", "😀").apply {
                    enabled = false
                },
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀"),
                Category("Grocery Store", "😀")
            )
        }
        transactionWithdrawFromIcon.setOnClickListener {
            transactionContainer.transitionToEnd()
            transactionContainer.getTransition(R.id.keyboardDown).setEnable(true)
            transactionCategoriesList.adapter = withdrawAdapter
            withdrawAdapter.items = listOf(
                Account("Cash", 1200).apply {
                    enabled = false
                },
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200),
                Account("Cash", 1200)
            )
        }
        transactionContainer.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentState: Int) {
                if (currentState == R.id.start) {
                    transactionContainer.getTransition(R.id.keyboardDown).setEnable(false)
                }
            }
        })
        transactionKeyboard.setUpWithTextView(transactionAmount)
        withdrawAdapter.onItemClick = {
            transactionWithdrawFrom.text = it.name
        }
        enrollmentAdapter.onItemClick = {
            transactionEnrollmentTo.text = it.name
        }
        view.doOnLayout {
            transactionCategoriesContainer.mHeight = transactionKeyboardContainer.height + context.dpToPx(64)
        }
    }
}
