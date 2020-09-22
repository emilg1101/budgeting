package com.github.emilg1101.budgeting.wallets.categories.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.wallets.R
import com.github.emilg1101.budgeting.wallets.categories.ui.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.wallets.main.di.AllAccountsComponent
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class CategoriesFragment : BaseFragment<CategoriesViewModel>(R.layout.fragment_categories) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    override val viewModel: CategoriesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AllAccountsComponent.component.categoriesSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categories.adapter = categoryAdapter
        observeViewModel()
        addCategory.setOnClickListener {
            findNavController().navigate(R2.id.createCategory)
        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.items = it
        })
        viewModel.stubVisibility.observe(viewLifecycleOwner, Observer {
            stub.isVisible = it
        })
    }
}
