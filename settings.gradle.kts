listOf(
    "app",
    "core",
    "data",
    "device",
    "domain",
    ":features:home",
    ":features:transaction",
    ":features:analytics",
    ":features:wallets",
    ":features:authorization",
    ":features:onboarding"
).forEach { module -> include(module) }