package com.github.emilg1101.budgeting.widget.core

interface WidgetProvider {
    fun provideWidget(): Widget<*>
    fun provideInjector(): WidgetInjector<*>
}
