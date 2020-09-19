package com.github.emilg1101.budgeting.transaction.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.qualifier.ShortDate
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.dpToPx
import com.github.emilg1101.budgeting.core.onClick
import com.github.emilg1101.budgeting.core.toCurrency
import com.github.emilg1101.budgeting.core.view.SpacesItemDecoration
import com.github.emilg1101.budgeting.transaction.R
import com.github.emilg1101.budgeting.transaction.di.TransactionComponent
import com.github.emilg1101.budgeting.transaction.domain.TransactionType
import com.github.emilg1101.budgeting.transaction.ui.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.transaction.widget.setUpWithTextView
import kotlinx.android.synthetic.main.fragment_transaction.*
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Named
import com.github.emilg1101.budgeting.R as R2

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

    @ShortDate
    @Inject
    lateinit var formatter: DateTimeFormatter

    override val viewModel: TransactionViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        TransactionComponent.init(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionCategoriesList.addItemDecoration(
            SpacesItemDecoration(context.dpToPx(10))
        )
        transactionContainer.getTransition(R.id.keyboardDown).setEnable(false)
        transactionClose.setOnClickListener { findNavController().navigateUp() }
        viewModel.enrollmentCategories.observe(viewLifecycleOwner, Observer {
            transactionCreateCategory.isVisible = it.isEmpty()
            enrollmentAdapter.items = it
        })
        viewModel.withdrawCategories.observe(viewLifecycleOwner, Observer {
            transactionCreateCategory.isVisible = it.isEmpty()
            withdrawAdapter.items = it
        })
        transactionEnrollmentToBox.setOnClickListener {
            transactionContainer.transitionToEnd()
            transactionContainer.getTransition(R.id.keyboardDown).setEnable(true)
            transactionCategoriesList.adapter = enrollmentAdapter
            transactionCreateCategory.text = "CREATE CATEGORY"
            transactionCreateCategory.setOnClickListener {
                findNavController().navigate(R2.id.createCategory)
            }
            transactionCreateCategory.isVisible =
                viewModel.enrollmentCategories.value.isNullOrEmpty()
        }
        transactionWithdrawFromBox.setOnClickListener {
            transactionContainer.transitionToEnd()
            transactionContainer.getTransition(R.id.keyboardDown).setEnable(true)
            transactionCategoriesList.adapter = withdrawAdapter
            transactionCreateCategory.text = "CREATE ACCOUNT"
            transactionCreateCategory.setOnClickListener {
                findNavController().navigate(R2.id.createAccount)
            }
            transactionCreateCategory.isVisible = viewModel.withdrawCategories.value.isNullOrEmpty()
        }
        transactionContainer.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentState: Int) {
                if (currentState == R.id.start) {
                    transactionContainer.getTransition(R.id.keyboardDown).setEnable(false)
                }
            }
        })
        viewModel.selectedWithdrawCategory.observe(viewLifecycleOwner, Observer {
            transactionWithdrawFrom.text = it?.name ?: ""
        })
        viewModel.selectedEnrollmentCategory.observe(viewLifecycleOwner, Observer {
            transactionEnrollmentTo.text = it?.name ?: ""
        })
        withdrawAdapter.onItemClick = viewModel::withdrawCategoryChange
        enrollmentAdapter.onItemClick = viewModel::enrollmentCategoryChange
        view.doOnLayout {
            transactionCategoriesContainer.mHeight =
                transactionKeyboardContainer.height + context.dpToPx(64)
        }
        transactionType.onClick = viewModel::toggleTransactionType
        viewModel.transactionType.observe(viewLifecycleOwner, Observer {
            when (it) {
                TransactionType.Income -> {
                    transactionType.text = "income"
                    transactionEnrollmentToIcon.setImageResource(R.drawable.ic_account)
                }
                TransactionType.Expense -> {
                    transactionType.text = "expense"
                    transactionEnrollmentToIcon.setImageResource(R.drawable.ic_category)
                }
            }
        })

        transactionDate.onClick = {
            findNavController().navigate(R2.id.datePicker)
        }
        viewModel.transactionDate.observe(viewLifecycleOwner, Observer {
            when (it) {
                is TransactionDate.Today -> transactionDate.text = "today"
                is TransactionDate.Day -> transactionDate.text = it.date.format(formatter)
            }
        })

        transactionKeyboard.setUpWithTextView(viewModel)
        viewModel.transactionAmount.observe(viewLifecycleOwner, Observer {
            transactionAmount.text = "₽ ${it.toCurrency("")}"
        })
        transactionAmount.onClick = transactionContainer::transitionToStart

        transactionQr.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                findNavController().navigate(R2.id.scanner)
            } else {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 123)
            }
        }

        transactionCreate.onClick = viewModel::onCreateTransactionClick
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        findNavController().navigate(R2.id.scanner)
    }
}
