plugins {
    id("LibraryPlugin")
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

    implementation(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.compiler)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}