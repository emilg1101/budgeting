package com.github.emilg1101.budgeting.core.di.component

import android.content.Context
import com.github.emilg1101.budgeting.core.di.module.CoreDataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoreDataModule::class])
@Singleton
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): CoreComponent
    }
}
