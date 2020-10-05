plugins {
    id("FeaturePlugin")
}

dependencies {
    api(project(":scanner-api"))
    implementation(Libraries.codeScanner)
}