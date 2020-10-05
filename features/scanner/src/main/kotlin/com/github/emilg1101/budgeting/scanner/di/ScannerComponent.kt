package com.github.emilg1101.budgeting.scanner.di

import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.scanner.ui.ScannerFragment
import dagger.Component

@ScannerScope
@Component(dependencies = [CoreComponent::class])
interface ScannerComponent {
    fun inject(scannerFragment: ScannerFragment)
}
