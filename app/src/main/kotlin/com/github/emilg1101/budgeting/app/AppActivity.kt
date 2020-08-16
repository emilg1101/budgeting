package com.github.emilg1101.budgeting.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.app.di.inject
import com.github.emilg1101.budgeting.core.base.BottomAppActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : BottomAppActivity() {

    override val bottomNavigationView: BottomNavigationView?
        get() = findViewById(R.id.bottom_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.my_nav_host_fragment))
        bottomNavigationView?.setupWithNavController(findNavController(R.id.my_nav_host_fragment))
    }
}
