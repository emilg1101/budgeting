package com.github.emilg1101.budgeting.authorization.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.emilg1101.budgeting.authorization.R
import com.github.emilg1101.budgeting.authorization.di.AuthorizationComponent
import com.github.emilg1101.budgeting.core.base.NestedFragment
import com.github.emilg1101.budgeting.core.di.viewmodel.ViewModelFactory
import com.github.emilg1101.budgeting.core.onClick
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

class AuthorizationFragment : NestedFragment<AuthorizationViewModel>(R.layout.fragment_authorization) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AuthorizationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AuthorizationComponent.init(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorizationButton.apply {
            setSize(SignInButton.SIZE_WIDE)
            onClick = viewModel::onSignInButtonClick
        }
        viewModel.gso.observe(viewLifecycleOwner, Observer {
            it?.let { gso ->
                val client = GoogleSignIn.getClient(requireActivity(), gso)
                startActivityForResult(client.signInIntent, AUTH_REQ_CODE)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            AUTH_REQ_CODE -> handleAuthResult(data)
        }
    }

    private fun handleAuthResult(intent: Intent?) {

        val taskWithResult = GoogleSignIn.getSignedInAccountFromIntent(intent)

        try {
            val account = taskWithResult.getResult(ApiException::class.java)
            viewModel.handleAuthSuccess(account)
        } catch (ex: ApiException) {
           // viewModel.handleAuthError(ex.localizedMessage)
        }
    }



    companion object {
        private const val AUTH_REQ_CODE = 0
    }
}
