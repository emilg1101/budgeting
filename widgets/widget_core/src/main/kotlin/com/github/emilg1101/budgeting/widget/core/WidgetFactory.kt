package com.github.emilg1101.budgeting.widget.core

class WidgetFactory(private val widgetDependencies: WidgetDependencies) {

    private val widgets = hashMapOf<String, WidgetProvider>()

    init {
        widgets[TOTAL_BALANCE_WIDGET] = Class.forName(TOTAL_BALANCE_WIDGET).kotlin.objectInstance as WidgetProvider
        widgets[ACCOUNTS_WIDGET] = Class.forName(ACCOUNTS_WIDGET).kotlin.objectInstance as WidgetProvider
    }

    fun getWidget(className: String): Widget<*>? {
        return widgets[className]?.let {
            it.provideInjector().inject(widgetDependencies)
            it.provideWidget()
        }
    }

    companion object {
        const val TOTAL_BALANCE_WIDGET = "com.github.emilg1101.budgeting.widget.balance.TotalBalanceWidgetProvider"
        const val ACCOUNTS_WIDGET = "com.github.emilg1101.budgeting.widget.accounts.AccountsWidgetProvider"
    }
}
