package com.github.emilg1101.budgeting

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import androidx.fragment.app.Fragment
import androidx.work.Configuration
import androidx.work.WorkManager
import com.github.emilg1101.budgeting.core.di.component.CoreComponent
import com.github.emilg1101.budgeting.core.di.component.DaggerCoreComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class BudgetingApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initEmoji()
        AndroidThreeTen.init(this)
        WorkManager.initialize(
            this,
            Configuration.Builder().setWorkerFactory(coreComponent.provideWorkerFactory()).build()
        )
    }

    private fun initEmoji() {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
            .setReplaceAll(true)
        EmojiCompat.init(config)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as BudgetingApplication).coreComponent
    }
}

fun Activity.coreComponent() = BudgetingApplication.coreComponent(this)
fun Fragment.coreComponent() = this.requireActivity().coreComponent()
