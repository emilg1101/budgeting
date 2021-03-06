import BuildPlugins.Versions.buildToolsVersion
import BuildPlugins.Versions.googleServicesVersion
import Libraries.AndroidX.Room.Versions.roomVersion
import Libraries.AndroidX.Versions.activityVersion
import Libraries.AndroidX.Versions.appcompatVersion
import Libraries.AndroidX.Versions.constraintlayoutVersion
import Libraries.AndroidX.Versions.coreVersion
import Libraries.AndroidX.Versions.fragmentVersion
import Libraries.AndroidX.Versions.lifecycleVersion
import Libraries.AndroidX.Versions.livedataVersion
import Libraries.AndroidX.Versions.materialVersion
import Libraries.AndroidX.Versions.multidexVersion
import Libraries.AndroidX.Versions.navigationVersion
import Libraries.AndroidX.Versions.pagingVersion
import Libraries.AndroidX.Versions.recyclerviewVersion
import Libraries.Dagger.Versions.daggerVersion
import Libraries.Firebase.Versions.firebaseAuthVersion
import Libraries.Glide.Versions.glideVersion
import Libraries.GoogleServices.Versions.authVersion
import Libraries.GoogleServices.Versions.kotlinxVersion
import Libraries.Retrofit.Versions.loggingVersion
import Libraries.Retrofit.Versions.retrofitVersion
import Libraries.Versions.codeScannerVersion
import Libraries.Versions.coroutinesVersion
import Libraries.Versions.emojiVersion
import Libraries.Versions.gsonVersion
import Libraries.Versions.timberVersion
import TestLibraries.Versions.androidJunitVersion
import TestLibraries.Versions.espressoVersion
import TestLibraries.Versions.junitVersion
import TestLibraries.Versions.testRunnerVersion

const val kotlinVersion = "1.3.61"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.1"
        const val detektVersion = "1.11.0-RC2"
        const val googleServicesVersion = "4.3.3"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:$buildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt"
    const val googleServicesPlugin = "com.google.gms:google-services:$googleServicesVersion"
}

object AndroidSdk {

    const val buildToolsVersion = "29.0.2"
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
}

object Libraries {

    object Versions {
        const val coroutinesVersion = "1.3.6"
        const val timberVersion = "4.7.1"
        const val gsonVersion = "2.8.6"
        const val codeScannerVersion = "2.1.0"
        const val emojiVersion = "28.0.0"
    }

    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
    const val codeScanner = "com.budiyev.android:code-scanner:$codeScannerVersion"
    const val emoji = "com.android.support:support-emoji:$emojiVersion"

    object AndroidX {

        object Versions {
            const val activityVersion = "1.0.0"
            const val fragmentVersion = "1.2.5"
            const val appcompatVersion = "1.1.0"
            const val coreVersion = "1.1.0"
            const val constraintlayoutVersion = "2.0.0-beta4"
            const val recyclerviewVersion = "1.2.0-alpha01"
            const val lifecycleVersion = "2.1.0"
            const val multidexVersion = "2.0.1"
            const val navigationVersion = "2.3.0"
            const val materialVersion = "1.3.0-alpha02"
            const val livedataVersion = "2.2.0-rc03"
            const val pagingVersion = "2.1.1"
        }

        const val activity = "androidx.activity:activity-ktx:$activityVersion"
        const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val core = "androidx.core:core-ktx:$coreVersion"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion"
        const val recyclerview = "androidx.recyclerview:recyclerview:$recyclerviewVersion"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$livedataVersion"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val multidex = "androidx.multidex:multidex:$multidexVersion"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val paging = "androidx.paging:paging-runtime:$pagingVersion"
        const val navigationDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"

        object Room {

            object Versions {
                const val roomVersion = "2.2.5"
            }

            const val runtime = "androidx.room:room-runtime:$roomVersion"
            const val compiler = "androidx.room:room-compiler:$roomVersion"
            const val ktx = "androidx.room:room-ktx:$roomVersion"
        }
    }

    object Glide {

        object Versions {
            const val glideVersion = "4.11.0"
        }

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val compiler = "com.github.bumptech.glide:compiler:$glideVersion"
    }

    object Retrofit {

        object Versions {
            const val retrofitVersion = "2.7.1"
            const val loggingVersion = "4.3.1"
        }

        const val jsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$loggingVersion"
    }

    object Dagger {

        object Versions {
            const val daggerVersion = "2.25.2"
        }

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object GoogleServices {

        object Versions {
            const val authVersion = "18.1.0"
            const val kotlinxVersion = "1.1.1"
        }

        const val auth = "com.google.android.gms:play-services-auth:$authVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$kotlinxVersion"
    }

    object Firebase {

        object Versions {
            const val firebaseAuthVersion = "19.3.2"
        }

        const val auth = "com.google.firebase:firebase-auth-ktx:$firebaseAuthVersion"
    }
}

object TestLibraries {

    object Versions {
        const val junitVersion = "4.13"
        const val androidJunitVersion = "1.1.1"
        const val espressoVersion = "3.2.0"
        const val testRunnerVersion = "1.1.0-alpha4"
    }

    const val junit4 = "junit:junit:$junitVersion"
    const val junitAndroid = "androidx.test.ext:junit:$androidJunitVersion"
    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
}
