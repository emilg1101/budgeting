plugins {
    id("LibraryPlugin")
}

dependencies {
    implementDomain()

    implementation(Libraries.coroutinesAndroid)

    implementation(Libraries.AndroidX.Room.runtime)
    implementation(Libraries.AndroidX.Room.ktx)
    kapt(Libraries.AndroidX.Room.compiler)

    implementation(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.compiler)

    implementation(Libraries.gson)

    implementation("com.google.firebase:firebase-auth-ktx:19.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1")

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}