package com.github.emilg1101.budgeting.home.widget.accounts

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.view.adapter.LifecycleViewHolder
import com.github.emilg1101.budgeting.home.di.HomeComponent
import com.github.emilg1101.budgeting.home.ui.Navigator
import com.github.emilg1101.budgeting.home.widget.accounts.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.widget_accounts.view.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class AccountsWidget(view: View) : LifecycleViewHolder(view) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModelStoreOwner: ViewModelStoreOwner

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var navigator: Navigator

    lateinit var viewModel: AccountsWidgetViewModel

    override fun onAppear() {
        HomeComponent.component.inject(this)
        super.onAppear()
        viewModel = ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(AccountsWidgetViewModel::class.java)
        itemView.widgetAccounts.layoutManager = GridLayoutManager(itemView.context, 2, HORIZONTAL, false)
        itemView.widgetAccounts.adapter = categoryAdapter
        /*itemView.widgetAccounts.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                itemView.context.dpToPx(10),
                true
            )
        )*/
        categoryAdapter.onAddClick = {
            navigator.navController.navigate(R2.id.createAccount)
        }
        viewModel.accounts.observe(this, Observer {
            categoryAdapter.items = it
        })
    }
}
