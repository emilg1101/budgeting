package com.github.emilg1101.budgeting.data

import android.content.Context
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    fun provideGoogleAccount(context: Context) = GoogleSignIn.getLastSignedInAccount(context)

    @Provides
    fun provideGoogleDriveService(acct: GoogleSignInAccount?, context: Context): Drive {
        val credential = GoogleAccountCredential.usingOAuth2(
            context, Collections.singleton(DriveScopes.DRIVE_APPDATA)
        )
        credential.selectedAccount = acct?.account
        return Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            GsonFactory(),
            credential
        )
            .setApplicationName("Budgeting")
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkManager(context: Context) =
        WorkManager.getInstance(context)
}
