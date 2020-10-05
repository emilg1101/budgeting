plugins {
    id(BuildPlugins.detektGradlePlugin) version BuildPlugins.Versions.detektVersion
}

buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.googleServicesPlugin)
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    apply {
        plugin(BuildPlugins.detektGradlePlugin)
    }
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}
