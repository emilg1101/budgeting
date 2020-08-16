@file:JvmName("Injector")

package com.github.emilg1101.budgeting.app.di

import com.github.emilg1101.budgeting.app.AppActivity
import com.github.emilg1101.budgeting.coreComponent

fun inject(activity: AppActivity) {
    DaggerAppComponent.builder()
        .coreComponent(activity.coreComponent())
        .build()
        .inject(activity)
}
