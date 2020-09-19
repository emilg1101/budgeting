package com.github.emilg1101.budgeting.analytics.balance.ui

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
import kotlinx.android.synthetic.main.fragment_balance.*
import javax.inject.Inject

class BalanceFragment : BaseFragment<BalanceViewModel>(R.layout.fragment_balance) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var formatter: XAxisValueFormatter

    override val viewModel: BalanceViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AnalyticsComponent.component.balanceSubcomponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balanceChart.legend.isEnabled = false
        balanceChart.axisRight.isEnabled = false
        balanceChart.xAxis.setDrawGridLines(false)
        balanceChart.description.isEnabled = false
        balanceChart.setNoDataTextColor(Color.WHITE)
        balanceChart.xAxis.textColor = Color.WHITE
        balanceChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        balanceChart.xAxis.axisMinimum = -1F
        balanceChart.xAxis.axisMaximum = 12F
        balanceChart.axisLeft.isEnabled = false
        balanceChart.setTouchEnabled(false)
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
            balanceChart.data = lineData
            formatter.items = it.keys.toList()
            balanceChart.xAxis.valueFormatter = formatter
            balanceChart.invalidate()
        })
        viewModel.balance.observe(viewLifecycleOwner, Observer {
            balanceTotal.text = it.toCurrency()
        })
    }
}
