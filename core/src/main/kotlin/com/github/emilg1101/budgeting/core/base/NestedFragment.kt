package com.github.emilg1101.budgeting.core.base

import androidx.annotation.LayoutRes

abstract class NestedFragment<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    BaseFragment<VM>(contentLayoutId)
