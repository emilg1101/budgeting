package com.github.emilg1101.budgeting.core.di.module

import android.content.Context
import com.github.emilg1101.budgeting.core.di.qualifier.*
import com.github.emilg1101.budgeting.core.withLocale
import dagger.Module
import dagger.Provides
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import javax.inject.Singleton

@Module
class DateTimeFormatModule {
    @Singleton
    @Provides
    @MediumDate
    fun provideMediumDateFormatter(
        context: Context
    ): DateTimeFormatter {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(context)
    }

    @Singleton
    @Provides
    @MediumDateTime
    fun provideDateTimeFormatter(
        context: Context
    ): DateTimeFormatter {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(context)
    }

    @Singleton
    @Provides
    @ShortDate
    fun provideShortDateFormatter(
        context: Context
    ): DateTimeFormatter {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(context)
    }

    @Singleton
    @Provides
    @ShortTime
    fun provideShortTimeFormatter(
        context: Context
    ): DateTimeFormatter {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(context)
    }

    @Singleton
    @Provides
    @ShortMonthDate
    fun provideShortMonthDateFormatter(
        context: Context
    ): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("MMM").withLocale(context)
    }
}
