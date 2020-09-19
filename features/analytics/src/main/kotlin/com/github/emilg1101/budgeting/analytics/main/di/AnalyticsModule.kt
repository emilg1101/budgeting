package com.github.emilg1101.budgeting.analytics.main.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.github.emilg1101.budgeting.analytics.formatter.XAxisValueFormatter
import com.github.emilg1101.budgeting.analytics.main.ui.AnalyticsFragment
import com.github.emilg1101.budgeting.analytics.main.ui.AnalyticsViewModel
import com.github.emilg1101.budgeting.analytics.main.ui.adapter.AnalyticsFragmentAdapter
import com.github.emilg1101.budgeting.core.di.qualifier.ShortMonthDate
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.threeten.bp.format.DateTimeFormatter

@Module
interface AnalyticsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnalyticsViewModel::class)
    fun provideAnalyticsViewModel(viewModel: AnalyticsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideValueFormatter(@ShortMonthDate formatter: DateTimeFormatter) =
            XAxisValueFormatter(formatter)

        @JvmStatic
        @Provides
        fun provideFragmentManager(fragment: AnalyticsFragment) = fragment.childFragmentManager

        @JvmStatic
        @Provides
        fun provideLifecycle(fragment: AnalyticsFragment) = fragment.lifecycle

        @JvmStatic
        @FeatureScope
        @Provides
        fun provideAnalyticsFragmentAdapter(
            fragmentManager: FragmentManager,
            lifecycle: Lifecycle
        ) = AnalyticsFragmentAdapter(fragmentManager, lifecycle)
    }
}
