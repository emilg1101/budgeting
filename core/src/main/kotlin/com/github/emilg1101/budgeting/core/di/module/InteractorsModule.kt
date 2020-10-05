package com.github.emilg1101.budgeting.core.di.module

import com.github.emilg1101.budgeting.core.interactors.ScannerInteractorImpl
import com.github.emilg1101.budgeting.scanner.api.ScannerInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun provideScannerInteractor(): ScannerInteractor = ScannerInteractorImpl()
}
