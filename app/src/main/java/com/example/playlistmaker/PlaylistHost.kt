package com.example.playlistmaker

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

@Composable
fun PlaylistHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MAIN.route) {
        composable(route = Screen.MAIN.route) {
            MainScreen(
                onSearchClick = { navController.navigate(Screen.SEARCH.route) },
                onSettingsClick = { navController.navigate(Screen.SETTINGS.route) }
            )
        }
        composable(route = Screen.SEARCH.route) {
            SearchScreen(onBackClick = { navController.popBackStack() })
        }
        composable(route = Screen.SETTINGS.route) {
            SettingsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}