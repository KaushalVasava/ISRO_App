package com.lahsuak.apps.isroinfo.ui.navigation

enum class Screen {
    SPLASH,
    LAUNCHES,
    SPACECRAFTS,
}

sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Launches : NavigationItem(Screen.LAUNCHES.name)
    data object SpaceCrafts : NavigationItem(Screen.SPACECRAFTS.name)
}