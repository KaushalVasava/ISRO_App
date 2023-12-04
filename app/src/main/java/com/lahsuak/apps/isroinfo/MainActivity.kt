package com.lahsuak.apps.isroinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lahsuak.apps.isroinfo.ui.components.BottomNavItem
import com.lahsuak.apps.isroinfo.ui.components.BottomNavigationBar
import com.lahsuak.apps.isroinfo.ui.navigation.AppNavHost
import com.lahsuak.apps.isroinfo.ui.navigation.NavigationItem
import com.lahsuak.apps.isroinfo.ui.theme.JetPackComposeBasicTheme
import com.lahsuak.apps.isroinfo.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val screens = listOf(
                        NavigationItem.Launches.route,
                        NavigationItem.SpaceCrafts.route
                    )
                    val showBottomBar = navController
                        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it }
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(
                                visible = showBottomBar,
                                enter = fadeIn() + scaleIn(),
                                exit = fadeOut() + scaleOut(),
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillMaxWidth()
                                ) {
                                    BottomNavigationBar(items = listOf(
                                        BottomNavItem(
                                            "Launches",
                                            NavigationItem.Launches.route,
                                            icon = painterResource(id = R.drawable.ic_launch)
                                        ),
                                        BottomNavItem(
                                            "SpaceCrafts",
                                            NavigationItem.SpaceCrafts.route,
                                            icon = painterResource(id = R.drawable.ic_spacecraft)
                                        ),

                                        ),
                                        navController = navController,
                                        onItemClick = {
                                            navController.navigate(it.route)
                                        }
                                    )
                                }
                            }
                        }) {
                        AppNavHost(
                            homeViewModel = viewModel,
                            navController = navController,
                            modifier = Modifier.padding(it)
                        )
                    }
                }
            }
        }
    }


    /***
    Composable functions :
    A composable function is a regular function annotated with @Composable.
    This enables your function to call other @Composable functions within it.
    You can see how the Greeting function is marked as @Composable.
    This function will produce a piece of UI hierarchy displaying the given input,
    String. Text is a composable function provided by the library.
     ***/
    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetPackComposeBasicTheme {
            Greeting("Android")
        }
    }
}