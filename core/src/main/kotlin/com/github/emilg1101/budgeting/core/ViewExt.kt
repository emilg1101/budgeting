package com.github.emilg1101.budgeting.core

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(snackbarText: String, timeLength: Int) {
    activity?.let {
        Snackbar.make(
            it.findViewById<View>(android.R.id.content),
            snackbarText,
            timeLength
        ).show()
    }
}

fun Fragment.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackbar(it.getString(res), timeLength) }
        }
    })
}

fun Context?.dpToPx(valueInDp: Int): Int {
    if (this == null) return 0
    val metrics = resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp.toFloat(), metrics).toInt()
}