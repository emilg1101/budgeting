package com.github.emilg1101.budgeting.analytics.main.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.github.emilg1101.budgeting.analytics.R
import com.github.emilg1101.budgeting.analytics.main.di.AnalyticsComponent
import com.github.emilg1101.budgeting.analytics.main.ui.adapter.AnalyticsFragmentAdapter
import com.github.emilg1101.budgeting.analytics.main.ui.adapter.TransactionsAdapter
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.qualifier.MediumDate
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.dpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.bottom_sheet_transactions.*
import kotlinx.android.synthetic.main.fragment_analytics.*
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.abs

class AnalyticsFragment :
    BaseFragment<AnalyticsViewModel>(R.layout.fragment_analytics) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @field:MediumDate
    @field:Inject
    lateinit var formatter: DateTimeFormatter

    @Inject
    lateinit var analyticsFragmentAdapter: AnalyticsFragmentAdapter

    override val viewModel: AnalyticsViewModel by viewModels { viewModelFactory }

    private var prevPosition = 0

    private val bottomSheetBehavior by lazy { BottomSheetBehavior.from(bottomSheetLayout) }
    private val defaultPeekHeight by lazy { requireContext().dpToPx(250) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AnalyticsComponent.init(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TransactionsAdapter(formatter)
        transactions.adapter = adapter
        viewModel.transactions.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        analyticsViewPager.adapter = analyticsFragmentAdapter
        analyticsViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffset == 0f) {
                    prevPosition = position
                }
                var newPositionOffset = positionOffset * 2
                if (newPositionOffset > 1) {
                    newPositionOffset = abs(newPositionOffset - 2)
                }
                val peek = (defaultPeekHeight * (1f - newPositionOffset)).toInt()
                if ((prevPosition == position || prevPosition - 1 == position) && positionOffset != 0f) {
                    bottomSheetBehavior.peekHeight = peek
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentTab(position)

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (ViewPager.SCROLL_STATE_IDLE == state) {
                    bottomSheetBehavior.peekHeight = defaultPeekHeight
                }
            }
        })

        TabLayoutMediator(analyticsTabs, analyticsViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "BALANCE"
                }
                1 -> {
                    tab.text = "INCOME"
                }
                2 -> {
                    tab.text = "EXPENSES"
                }
            }
        }.attach()
        view.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
        view.doOnLayout {
            bottomSheetBehavior.peekHeight = defaultPeekHeight
        }
    }
}
