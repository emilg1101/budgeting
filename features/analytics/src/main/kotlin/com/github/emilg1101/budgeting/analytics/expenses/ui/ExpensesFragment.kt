package com.github.emilg1101.budgeting.analytics.expenses.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.emilg1101.budgeting.analytics.R
import com.github.emilg1101.budgeting.analytics.formatter.XAxisValueFormatter
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsComponent
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.toCurrency
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_expenses.*
import javax.inject.Inject

class ExpensesFragment : BaseFragment<ExpensesViewModel>(R.layout.fragment_expenses) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var formatter: XAxisValueFormatter

    override val viewModel: ExpensesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AnalyticsComponent.component.expensesSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expensesChart.legend.isEnabled = false
        expensesChart.axisRight.isEnabled = false
        expensesChart.xAxis.setDrawGridLines(false)
        expensesChart.description.isEnabled = false
        expensesChart.setNoDataTextColor(Color.WHITE)
        expensesChart.xAxis.textColor = Color.WHITE
        expensesChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        expensesChart.xAxis.axisMinimum = -1F
        expensesChart.xAxis.axisMaximum = 12F
        expensesChart.axisLeft.isEnabled = false
        expensesChart.setTouchEnabled(false)
        viewModel.report.observe(viewLifecycleOwner, Observer {
            val dataSet = LineDataSet(
                it.values.reversed().mapIndexed { index: Int, l: Long ->
                    Entry(index.toFloat(), l.toFloat())
                },
                ""
            )
            dataSet.valueFormatter = formatter
            dataSet.valueTextColor = Color.WHITE
            dataSet.valueTextSize = 10f
            val lineData = LineData(dataSet)
            expensesChart.data = lineData
            formatter.items = it.keys.toList()
            expensesChart.xAxis.valueFormatter = formatter
            expensesChart.invalidate()
        })
        viewModel.totalExpenses.observe(viewLifecycleOwner, Observer {
            expenses.text = it.second.toCurrency()
        })
    }
}
