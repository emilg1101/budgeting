package com.github.emilg1101.budgeting.scanner.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.github.emilg1101.budgeting.core.base.BottomBarCovering
import com.github.emilg1101.budgeting.coreComponent
import com.github.emilg1101.budgeting.scanner.R
import com.github.emilg1101.budgeting.scanner.api.ScannerInteractor
import com.github.emilg1101.budgeting.scanner.api.ScannerResult
import com.github.emilg1101.budgeting.scanner.di.DaggerScannerComponent
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class ScannerFragment : Fragment(R.layout.fragment_scanner), BottomBarCovering {

    @Inject
    lateinit var scannerInteractor: ScannerInteractor

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerScannerComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val map = it.text.split("&").map {
                    val args = it.split("=")
                    args[0] to args[1]
                }.toMap()
                scannerInteractor.setScannerResult(ScannerResult((map["s"]?.toFloatOrNull()?.toLong() ?: 0) * 100, OffsetDateTime.now()))
                findNavController().navigateUp()
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}