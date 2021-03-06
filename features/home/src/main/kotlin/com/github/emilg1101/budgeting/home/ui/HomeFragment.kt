package com.github.emilg1101.budgeting.home.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.github.emilg1101.budgeting.core.base.BaseFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.dpToPx
import com.github.emilg1101.budgeting.core.onClick
import com.github.emilg1101.budgeting.core.view.SpacesItemDecoration
import com.github.emilg1101.budgeting.home.R
import com.github.emilg1101.budgeting.home.adapter.WidgetAdapter
import com.github.emilg1101.budgeting.home.di.HomeComponent
import com.github.emilg1101.budgeting.widget.core.WidgetFactory
import com.github.emilg1101.budgeting.widget.core.WidgetFactory.Companion.ACCOUNTS_WIDGET
import com.github.emilg1101.budgeting.widget.core.WidgetFactory.Companion.TOTAL_BALANCE_WIDGET
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragment_home), Navigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var widgetAdapter: WidgetAdapter

    @Inject
    lateinit var widgetFactory: WidgetFactory

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override val navController: NavController
        get() = findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        HomeComponent.init(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeWidgets.addItemDecoration(SpacesItemDecoration(context.dpToPx(16)))
        homeWidgets.adapter = widgetAdapter
        widgetAdapter.widgets = listOfNotNull(
            widgetFactory.getWidget(TOTAL_BALANCE_WIDGET),
            widgetFactory.getWidget(ACCOUNTS_WIDGET)
        )
        homeSettings.onClick = {
            findNavController().navigate(R2.id.settings)
        }
        viewModel.user.observe(viewLifecycleOwner, Observer {
            homeProfileName.text = it.name
            Glide.with(homeProfileImage).load(it.image).transform(CircleCrop())
                .into(homeProfileImage)
        })
        homeToolbar.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }
}
