plugins {
    id("FeaturePlugin")
}

dependencies {
    implementWidgetCore()
    implementation(Libraries.Glide.glide)
    implementation("com.github.florent37:shapeofview:1.4.7")
}