include(":scanner-api")
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
    ":features:onboarding",
    "features:scanner",
    ":widgets:widget_core",
    ":widgets:widget_balance",
    ":widgets:widget_accounts"
).forEach { module -> include(module) }