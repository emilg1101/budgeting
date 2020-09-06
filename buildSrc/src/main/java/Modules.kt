import org.gradle.api.artifacts.dsl.DependencyHandler

object Modules {
    const val App = ":app"
    const val Core = ":core"
    const val Domain = ":domain"
    const val Data = ":data"
    const val Device = ":device"
    const val WidgetCore = ":widgets:widget_core"
}

fun DependencyHandler.implementApp() {
    add("implementation", project(mapOf("path" to Modules.App)))
}

fun DependencyHandler.implementCore() {
    add("implementation", project(mapOf("path" to Modules.Core)))
}

fun DependencyHandler.implementDomain() {
    add("api", project(mapOf("path" to Modules.Domain)))
}

fun DependencyHandler.implementData() {
    add("implementation", project(mapOf("path" to Modules.Data)))
}

fun DependencyHandler.implementDevice() {
    add("implementation", project(mapOf("path" to Modules.Device)))
}

fun DependencyHandler.implementWidgetCore() {
    add("implementation", project(mapOf("path" to Modules.WidgetCore)))
}
