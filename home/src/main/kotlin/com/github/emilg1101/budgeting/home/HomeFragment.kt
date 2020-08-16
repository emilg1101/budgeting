package com.github.emilg1101.budgeting.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.home.di.DaggerHomeComponent
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragment_home) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerHomeComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }
}
