package com.github.emilg1101.budgeting.widget.accounts

import com.github.emilg1101.budgeting.widget.accounts.di.AccountsInjector
import com.github.emilg1101.budgeting.widget.accounts.ui.AccountsWidgetViewHolder
import com.github.emilg1101.budgeting.widget.core.Widget
import com.github.emilg1101.budgeting.widget.core.WidgetInjector
import com.github.emilg1101.budgeting.widget.core.WidgetProvider

object AccountsWidgetProvider : WidgetProvider {

    override fun provideWidget(): Widget<AccountsWidgetViewHolder> {
        return Widget(
            R.layout.widget_accounts,
            AccountsWidgetViewHolder::class.java,
            TAG
        )
    }

    override fun provideInjector(): WidgetInjector<*> {
        return AccountsInjector
    }

    private const val TAG = "widget_accounts"
}
