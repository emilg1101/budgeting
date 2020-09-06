package com.github.emilg1101.budgeting.widget.core

import androidx.annotation.LayoutRes
import com.github.emilg1101.budgeting.core.view.adapter.LifecycleViewHolder

data class Widget<T : LifecycleViewHolder>(
    @LayoutRes val widgetLayout: Int,
    val viewHolderClass: Class<T>,
    val tag: String
)
