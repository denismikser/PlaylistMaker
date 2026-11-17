package com.example.playlistmaker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.playlistmaker.ui.search.SearchScreen
import com.example.playlistmaker.ui.setting.SettingsScreen

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