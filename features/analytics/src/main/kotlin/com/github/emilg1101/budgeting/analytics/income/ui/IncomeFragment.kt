package com.github.emilg1101.budgeting.analytics.income.ui

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
import kotlinx.android.synthetic.main.fragment_income.*
import javax.inject.Inject

class IncomeFragment : BaseFragment<IncomeViewModel>(R.layout.fragment_income) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var formatter: XAxisValueFormatter

    override val viewModel: IncomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AnalyticsComponent.component.incomeSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeChart.legend.isEnabled = false
        incomeChart.axisRight.isEnabled = false
        incomeChart.xAxis.setDrawGridLines(false)
        incomeChart.description.isEnabled = false
        incomeChart.setNoDataTextColor(Color.WHITE)
        incomeChart.xAxis.textColor = Color.WHITE
        incomeChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        incomeChart.xAxis.axisMinimum = -1F
        incomeChart.xAxis.axisMaximum = 12F
        incomeChart.axisLeft.isEnabled = false
        incomeChart.setTouchEnabled(false)
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
            incomeChart.data = lineData
            formatter.items = it.keys.toList()
            incomeChart.xAxis.valueFormatter = formatter
            incomeChart.invalidate()
        })
        viewModel.totalIncome.observe(viewLifecycleOwner, Observer {
            income.text = it.second.toCurrency()
        })
    }
}
