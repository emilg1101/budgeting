plugins {
    id("LibraryPlugin")
}

dependencies {
    implementDomain()
    implementData()
    implementDevice()

    implementation(Libraries.coroutinesAndroid)

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

    implementation(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.compiler)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}