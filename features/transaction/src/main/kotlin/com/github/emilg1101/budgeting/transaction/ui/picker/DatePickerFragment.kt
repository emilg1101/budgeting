package com.github.emilg1101.budgeting.transaction.ui.picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.github.emilg1101.budgeting.transaction.di.TransactionComponent
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import java.util.*
import javax.inject.Inject

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    @Inject
    lateinit var dateTimePickerCallback: DateTimePickerCallback

    private val pickedCalendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TransactionComponent.component?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        pickedCalendar.set(Calendar.YEAR, year)
        pickedCalendar.set(Calendar.MONTH, month)
        pickedCalendar.set(Calendar.DAY_OF_MONTH, day)
        TimePickerDialog(requireContext(), this, hourOfDay, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val year = pickedCalendar.get(Calendar.YEAR)
        val month = pickedCalendar.get(Calendar.MONTH)
        val day = pickedCalendar.get(Calendar.DAY_OF_MONTH)
        dateTimePickerCallback.onDateTimePicked(OffsetDateTime.of(year, month+1, day, hourOfDay, minute, 0, 0, ZoneOffset.UTC))
    }
}
