package com.github.emilg1101.budgeting.data

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.milliseconds

const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
const val FORMAT_DD_MM_YYYY = "dd-MM-yyyy"
const val FORMAT_DDMMYYYY = "dd.MM.yyyy"
const val FORMAT_HH_mm = "HH:mm"

private val dateTimeFormatFactory = { format: String ->
    SimpleDateFormat(format, Locale.US)
}

fun Calendar.format(format: String = FORMAT_YYYY_MM_DD) = this.time.format(format)

fun String.toCalendar(format: String = FORMAT_YYYY_MM_DD): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = dateTimeFormatFactory.invoke(format).parse(this) ?: throw Exception()
    return calendar
}

fun String.toDate(format: String = FORMAT_YYYY_MM_DD) =
    if (this.isEmpty()) {
        null
    } else {
        this.toCalendar(format).time
    }

fun Date.format(format: String = FORMAT_DDMMYYYY): String = dateTimeFormatFactory.invoke(format).format(this)

fun Date?.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    if (this != null) {
        calendar.time = this
    }
    return calendar
}

fun Calendar.dayOfWeekDayMonth() = "${this.dayOfWeek()}, ${this.dayOfMonth()}"

fun Calendar.shortDayOfWeekMonthYear() = "${this.shortDayOfWeek(true)}, ${this.dayOfMonthYear(isShort = true)}"

fun Calendar.shortDayOfWeek(isUpperCase: Boolean = false): String {
    var day = when (this.get(Calendar.DAY_OF_WEEK)) {
        1 -> "Вс"
        2 -> "Пн"
        3 -> "Вт"
        4 -> "Ср"
        5 -> "Чт"
        6 -> "Пт"
        else -> "Сб"
    }
    day = if (isUpperCase) day.toUpperCase(Locale.getDefault()) else day
    return day
}

fun Calendar.dayOfWeek() =
    when (this.get(Calendar.DAY_OF_WEEK)) {
        1 -> "Воскресенье"
        2 -> "Понедельник"
        3 -> "Вторник"
        4 -> "Среда"
        5 -> "Четверг"
        6 -> "Пятница"
        else -> "Суббота"
    }

fun Calendar.dayOfMonth(isLowercase: Boolean = true, isShort: Boolean = false): String {
    var month = when (this.get(Calendar.MONTH)) {
        0 -> "Января"
        1 -> "Февраля"
        2 -> "Марта"
        3 -> "Апреля"
        4 -> "Мая"
        5 -> "Июня"
        6 -> "Июля"
        7 -> "Августа"
        8 -> "Сентября"
        9 -> "Октября"
        10 -> "Ноября"
        else -> "Декабря"
    }
    month = if (isLowercase) month.toLowerCase(Locale.getDefault()) else month
    month = if (isShort) month.substring(0, 3) else month
    return "${this.day()} $month"
}

fun Calendar.dayOfMonthYear(isLowercase: Boolean = true, isShort: Boolean = false, separator: String = ""): String {
    return "${this.dayOfMonth(isLowercase, isShort)}$separator ${this.get(Calendar.YEAR)}"
}

fun Calendar.dayOfMonthWithHour(separator: String = ""): String {
    return "${this.dayOfMonth()}$separator ${this.format(FORMAT_HH_mm)}"
}

fun Calendar.day() = get(Calendar.DAY_OF_MONTH)

fun Date.dayOfMonthYear(): String {
    val calendar = Calendar.getInstance().apply { time = this@dayOfMonthYear }
    return "${calendar.dayOfMonth()} ${calendar.get(Calendar.YEAR)}"
}

fun Calendar.clearCalendarTime(): Calendar {
    return this.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        clear(Calendar.MINUTE)
        clear(Calendar.SECOND)
        clear(Calendar.MILLISECOND)
    }
}

fun OffsetDateTime.withYearMonth() = OffsetDateTime.of(this.year, this.monthValue, 1, 0, 0, 0, 0, ZoneOffset.UTC)
