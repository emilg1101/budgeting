package com.github.emilg1101.budgeting.plugin

import AndroidSdk
import Libraries
import Libraries.AndroidX
import TestLibraries
import com.android.build.gradle.*
import com.github.emilg1101.budgeting.extensions.androidTestImplementation
import com.github.emilg1101.budgeting.extensions.implementation
import com.github.emilg1101.budgeting.extensions.kapt
import com.github.emilg1101.budgeting.extensions.testImplementation
import implementApp
import implementCore
import implementDomain
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class FeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.dynamic-feature")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-kapt")
        target.plugins.apply("kotlin-android-extensions")
        target.configureAndroid()
        target.applyFeatureLibs()
    }

    private fun Project.applyFeatureLibs() = dependencies {
        implementApp()
        implementCore()
        implementDomain()

        implementation(Libraries.coroutinesAndroid)
        implementation(Libraries.emoji)

        implementation(AndroidX.core)
        implementation(AndroidX.appcompat)
        implementation(AndroidX.activity)
        implementation(AndroidX.fragment)
        implementation(AndroidX.lifecycleExtensions)
        implementation(AndroidX.lifecycleLiveData)
        implementation(AndroidX.lifecycleViewModel)
        implementation(AndroidX.material)
        implementation(AndroidX.constraintlayout)
        implementation(AndroidX.navigationFragment)
        implementation(AndroidX.navigationUI)

        implementation(Libraries.Dagger.dagger)
        kapt(Libraries.Dagger.compiler)

        testImplementation(TestLibraries.junit4)
        androidTestImplementation(TestLibraries.junitAndroid)
        androidTestImplementation(TestLibraries.testRunner)
        androidTestImplementation(TestLibraries.espresso)
    }
}

internal fun Project.configureAndroid() = extensions.getByType(BaseExtension::class.java).run {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    buildToolsVersion(AndroidSdk.buildToolsVersion)
    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    project.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
    }
}
