package com.github.emilg1101.budgeting.onboarding.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.onboarding.R
import com.github.emilg1101.budgeting.onboarding.di.DaggerOnboardingComponent
import com.github.emilg1101.budgeting.onboarding.di.OnboardingComponent
import javax.inject.Inject

class OnboardingFlowFragment :
    NestedFragment<OnboardingFlowViewModel>(R.layout.fragment_onboarding_flow) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var component: OnboardingComponent

    override val viewModel: OnboardingFlowViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerOnboardingComponent.builder().coreComponent(coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }
}

fun Fragment.onboardingComponent() =
    (parentFragment?.parentFragment as OnboardingFlowFragment).component
