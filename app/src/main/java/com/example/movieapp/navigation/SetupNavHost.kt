package com.example.movieapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieapp.utils.Constants
import androidx.navigation.compose.composable
import com.example.movieapp.MainViewModel
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.MainScreen
import com.example.movieapp.screens.SplashScreen

sealed class Screens(val route: String) {
    data object Splash: Screens(route = Constants.Screens.SPLASH_SCREEN)
    data object Main: Screens(route = Constants.Screens.MAIN_SCREEN)
    data object Detail: Screens(route = Constants.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navHostController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Splash.route,
        ){
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screens.Detail.route + "/{Id}") { navBackStackEntry ->
                DetailScreen(
                    viewModel = viewModel,
                    itemId = navBackStackEntry.arguments?.getString("Id"  ) ?: "1"
                )
        }
    }
}