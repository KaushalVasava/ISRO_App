package com.lahsuak.apps.isroinfo.ui.navigation

enum class Screen {
    SPLASH,
    LAUNCHES,
    SPACECRAFTS,
}

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Launches : NavigationItem(Screen.LAUNCHES.name)
    object SpaceCrafts : NavigationItem(Screen.SPACECRAFTS.name)
}