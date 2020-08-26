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

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}