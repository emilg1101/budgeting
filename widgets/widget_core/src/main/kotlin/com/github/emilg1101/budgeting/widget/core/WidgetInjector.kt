package com.github.emilg1101.budgeting.widget.core

interface WidgetInjector<Component> {
    fun inject(dependencies: WidgetDependencies): Component
}
