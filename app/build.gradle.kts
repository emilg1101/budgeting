import com.github.emilg1101.budgeting.extensions.implementation

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    buildToolsVersion(AndroidSdk.buildToolsVersion)
    defaultConfig {
        applicationId = "com.github.emilg1101.budgeting"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = Versioning.version.code
        versionName = Versioning.version.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /*buildFeatures {
        viewBinding = true
    }*/

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
    }
    dynamicFeatures = mutableSetOf(
        ":features:home",
        ":features:transaction",
        ":features:analytics",
        ":features:wallets",
        ":features:authorization",
        ":features:onboarding",
        ":features:scanner",
        ":features:create_account",
        ":features:create_category",
        ":features:settings",
        ":widgets:widget_balance",
        ":widgets:widget_accounts"
    )
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
    }
}

dependencies {
    implementCore()

    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.emoji)

    implementation(Libraries.AndroidX.core)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.activity)
    implementation(Libraries.AndroidX.fragment)
    implementation(Libraries.AndroidX.lifecycleExtensions)
    implementation(Libraries.AndroidX.lifecycleLiveData)
    implementation(Libraries.AndroidX.lifecycleViewModel)
    implementation(Libraries.AndroidX.material)
    implementation(Libraries.AndroidX.constraintlayout)
    implementation(Libraries.AndroidX.navigationFragment)
    implementation(Libraries.AndroidX.navigationUI)
    implementation(Libraries.AndroidX.navigationDynamicFeature)

    implementation(Libraries.Glide.glide)
    kapt(Libraries.Glide.compiler)

    implementation(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.compiler)

    implementation("com.google.firebase:firebase-analytics:17.5.0")
    implementation("org.threeten:threetenbp:1.4.4:no-tzdb")
    implementation("com.jakewharton.threetenabp:threetenabp:1.2.4")
    implementation("androidx.work:work-runtime:2.4.0")
    implementation("com.google.guava:guava:27.0.1-android")

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}

apply(plugin = "com.google.gms.google-services")