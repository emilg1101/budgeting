package com.github.emilg1101.budgeting.authorization.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.github.emilg1101.budgeting.authorization.domain.AuthUserUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.github.emilg1101.budgeting.R as R2

@FeatureScope
class AuthorizationViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
) : BaseViewModel() {

    private val _gso = MutableLiveData<GoogleSignInOptions?>()
    val gso: LiveData<GoogleSignInOptions?> = _gso

    fun onSignInButtonClick() {
        val gsoReq = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("190478081233-6fpi2p8su255j7fenef0jr12c59rqqr2.apps.googleusercontent.com")
            .requestEmail()
            .build()

        _gso.value = gsoReq
    }

    fun handleAuthSuccess(account: GoogleSignInAccount?) {
        viewModelScope.launch {
            account?.idToken?.let { idToken ->
                authUserUseCase.invoke(idToken).collect {
                    if (!it.isNewUser) {
                        navigate(ActionOnlyNavDirections(R2.id.onboarding))
                    } else {
                        navigate(ActionOnlyNavDirections(R2.id.action_authorization_to_home))
                    }
                }
            }
        }

    }
}
