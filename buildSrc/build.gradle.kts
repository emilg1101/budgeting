repositories {
    google()
    mavenCentral()
    jcenter()
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("jvm") version "1.3.71"
}

gradlePlugin {
    plugins {
        register("LibraryPlugin") {
            id = "LibraryPlugin"
            implementationClass = "com.github.emilg1101.budgeting.plugin.LibraryPlugin"
        }
        register("FeaturePlugin") {
            id = "FeaturePlugin"
            implementationClass = "com.github.emilg1101.budgeting.plugin.FeaturePlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    compileOnly(gradleApi())

    implementation(kotlin("gradle-plugin", "1.3.71"))
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation(kotlin("android-extensions"))
}