plugins {
    id("LibraryPlugin")
}

dependencies {
    implementation("androidx.work:work-runtime:2.4.0")
    implementation("androidx.work:work-runtime-ktx:2.4.0")
    implementDomain()

    implementation(Libraries.coroutinesAndroid)

    api(Libraries.AndroidX.Room.runtime)
    implementation(Libraries.AndroidX.Room.ktx)
    kapt(Libraries.AndroidX.Room.compiler)

    implementation(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.compiler)

    implementation(Libraries.gson)

    implementation(Libraries.Firebase.auth)
    implementation(Libraries.GoogleServices.auth)
    implementation(Libraries.GoogleServices.coroutines)

    implementation("com.google.http-client:google-http-client-gson:1.26.0")
    implementation("com.google.api-client:google-api-client-android:1.26.0") {
        exclude(group = "org.apache.httpcomponents")
    }
    implementation("com.google.apis:google-api-services-drive:v3-rev136-1.25.0") {
        exclude(group = "org.apache.httpcomponents")
    }

    implementation("org.threeten:threetenbp:1.4.4:no-tzdb")

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitAndroid)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}