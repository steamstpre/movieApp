package com.example.movieapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieapp.utils.Constants
import androidx.navigation.compose.composable
import com.example.movieapp.screens.MainScreen
import com.example.movieapp.screens.SplashScreen

sealed class Screens(val route: String) {
    data object Splash: Screens(route = Constants.Screens.SPLASH_SCREEN)
    data object Main: Screens(route = Constants.Screens.MAIN_SCREEN)
    data object Detail: Screens(route = Constants.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Splash.route,
        ){
        composable(route = "splash_screen") {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screens.Main.route) {
            MainScreen()
        }
        composable(route = Screens.Detail.route) {

        }
    }
}