package com.github.emilg1101.budgeting.onboarding.categories_setup.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.onClick
import com.github.emilg1101.budgeting.onboarding.R
import com.github.emilg1101.budgeting.onboarding.adapter.CategoryAdapter
import com.github.emilg1101.budgeting.onboarding.ui.onboardingComponent
import kotlinx.android.synthetic.main.fragment_categories_setup.*
import javax.inject.Inject

class CategoriesSetupFragment : NestedFragment<CategoriesSetupViewModel>(R.layout.fragment_categories_setup) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    override val viewModel: CategoriesSetupViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        onboardingComponent().categoriesSetupSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesList.adapter = categoryAdapter
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.items = it
        })
        categoriesSetupNext.onClick = {
            viewModel.onNextButtonClick()
            findNavController().navigate(R.id.accounts_setup)
        }
    }
}
