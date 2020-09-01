package com.github.emilg1101.budgeting.onboarding.accounts_setup.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.onClick
import com.github.emilg1101.budgeting.onboarding.R
import com.github.emilg1101.budgeting.onboarding.ui.onboardingComponent
import kotlinx.android.synthetic.main.fragment_accounts_setup.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class AccountsSetupFragment :
    NestedFragment<AccountsSetupViewModel>(R.layout.fragment_accounts_setup) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AccountsSetupViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        onboardingComponent().accountsSetupSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountsSetupBack.onClick = {
            findNavController().navigateUp()
        }
        accountsSetupNext.onClick = {
            parentFragment?.parentFragment?.findNavController()?.navigate(R2.id.action_onboarding_to_home)
        }
    }
}
