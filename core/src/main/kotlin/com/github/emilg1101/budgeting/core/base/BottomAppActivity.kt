package com.github.emilg1101.budgeting.core.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception

abstract class BottomAppActivity : AppCompatActivity() {

    abstract val bottomNavigationView: BottomNavigationView?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                when (f) {
                    is BottomBarCovering -> {
                        bottomNavigationView?.visibility = View.GONE
                    }
                    else -> {
                        bottomNavigationView?.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                val lastFragment = try {
                    fm.fragments.getOrNull(0)?.childFragmentManager?.fragments?.lastOrNull()
                } catch (e: Exception) {
                }
                if (f is BottomBarCovering && lastFragment !is BottomBarCovering) {
                    bottomNavigationView?.visibility = View.VISIBLE
                }
                super.onFragmentDestroyed(fm, f)
            }
        }, true)
    }
}
