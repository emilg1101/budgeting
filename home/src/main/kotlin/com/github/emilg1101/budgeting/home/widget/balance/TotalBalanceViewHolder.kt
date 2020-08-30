package com.github.emilg1101.budgeting.home.widget.balance

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.view.adapter.LifecycleViewHolder
import com.github.emilg1101.budgeting.home.di.HomeComponent
import kotlinx.android.synthetic.main.widget_total_balance.view.*
import javax.inject.Inject

class TotalBalanceViewHolder(view: View): LifecycleViewHolder(view) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModelStoreOwner: ViewModelStoreOwner

    lateinit var viewModel: TotalBalanceViewModel

    override fun onAppear() {
        HomeComponent.component.inject(this)
        super.onAppear()
        viewModel = ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(TotalBalanceViewModel::class.java)
        viewModel.balance.observe(this, Observer {
            itemView.balanceAmount.text = "$it ₽"
        })
    }
}
