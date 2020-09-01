package com.github.emilg1101.budgeting.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.app.di.inject
import com.github.emilg1101.budgeting.core.base.BottomAppActivity
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AppActivity : BottomAppActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AppViewModel by viewModels { viewModelFactory }

    override val bottomNavigationView: BottomNavigationView?
        get() = findViewById(R.id.bottom_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.hasLoggedInUser.observe(this, Observer {
            val navHostFragment = my_nav_host_fragment as NavHostFragment
            val graphInflater = navHostFragment.navController.navInflater
            val navGraph = graphInflater.inflate(R.navigation.navigation)
            val navController = navHostFragment.navController

            val destination = if (it.getContentIfNotHandled() == true) {
                R.id.home
            } else {
                R.id.authorization
            }
            navGraph.startDestination = destination
            navController.graph = navGraph
            bottomNavigationView?.setupWithNavController(findNavController(R.id.my_nav_host_fragment))
        })
    }
}
