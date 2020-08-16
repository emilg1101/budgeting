package com.github.emilg1101.budgeting.plugin

import AndroidSdk
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-kapt")
        target.plugins.apply("kotlin-android-extensions")
        target.configureAndroid()
    }

    private fun Project.configureAndroid() = extensions.getByType(LibraryExtension::class.java).run {
        compileSdkVersion(AndroidSdk.compileSdkVersion)
        buildToolsVersion(AndroidSdk.buildToolsVersion)
        defaultConfig {
            minSdkVersion(AndroidSdk.minSdkVersion)
            targetSdkVersion(AndroidSdk.targetSdkVersion)

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}