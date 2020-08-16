package com.github.emilg1101.budgeting.wallets

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.wallets.di.DaggerWalletsComponent
import javax.inject.Inject

class WalletsFragment : BaseFragment<WalletsViewModel>(R.layout.fragment_wallets) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: WalletsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWalletsComponent.builder().coreComponent(this.coreComponent()).build().inject(this)
        super.onCreate(savedInstanceState)
    }
}
