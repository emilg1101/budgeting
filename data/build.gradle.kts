plugins {
    id("LibraryPlugin")
}

dependencies {
    implementDomain()

    implementation(Libraries.coroutinesAndroid)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}