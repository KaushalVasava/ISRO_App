package com.lahsuak.apps.isroinfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lahsuak.apps.isroinfo.ui.screen.HomeScreen
import com.lahsuak.apps.isroinfo.ui.screen.SpaceCraftScreen
import com.lahsuak.apps.isroinfo.ui.screen.SplashScreen
import com.lahsuak.apps.isroinfo.ui.viewmodel.HomeViewModel



@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination =if(isSplashScreenFinished){
            NavigationItem.Launches.route
        } else {
            NavigationItem.Splash.route
        }
    ) {
        composable(NavigationItem.Splash.route){
            SplashScreen {
                navController.navigate(NavigationItem.Launches.route)
                isSplashScreenFinished = true
            }
        }
        composable(NavigationItem.Launches.route) {
            HomeScreen(homeViewModel)
        }
        composable(NavigationItem.SpaceCrafts.route) {
            SpaceCraftScreen(homeViewModel)
        }
    }
}