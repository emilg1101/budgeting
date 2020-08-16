package com.github.emilg1101.budgeting.analytics

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.emilg1101.budgeting.analytics.di.DaggerAnalyticsComponent
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import javax.inject.Inject

class AnalyticsFragment : BaseFragment<AnalyticsViewModel>(R.layout.fragment_analytics) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AnalyticsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAnalyticsComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }
}
