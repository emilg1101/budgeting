package com.github.emilg1101.budgeting.widget.balance

import com.github.emilg1101.budgeting.widget.balance.di.TotalBalanceInjector
import com.github.emilg1101.budgeting.widget.balance.ui.TotalBalanceViewHolder
import com.github.emilg1101.budgeting.widget.core.Widget
import com.github.emilg1101.budgeting.widget.core.WidgetInjector
import com.github.emilg1101.budgeting.widget.core.WidgetProvider

object TotalBalanceWidgetProvider : WidgetProvider {

    override fun provideWidget(): Widget<TotalBalanceViewHolder> {
        return Widget(
            R.layout.widget_total_balance,
            TotalBalanceViewHolder::class.java,
            TAG
        )
    }

    override fun provideInjector(): WidgetInjector<*> {
        return TotalBalanceInjector
    }

    private const val TAG = "widget_balance"
}
