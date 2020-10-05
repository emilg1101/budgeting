plugins {
    id("LibraryPlugin")
}

dependencies {
    implementDomain()
    implementData()
    implementDevice()
    api(project(":scanner-api"))

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

    implementation(Libraries.gson)

    implementation(Libraries.Firebase.auth)
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
    implementation("androidx.work:work-runtime:2.4.0")
}