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

    implementation(Libraries.Firebase.auth)
    implementation(Libraries.GoogleServices.coroutines)

    implementation("org.threeten:threetenbp:1.4.4:no-tzdb")

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}