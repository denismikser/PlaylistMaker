package com.example.playlistmaker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.playlistmaker.domain.model.Track
import com.example.playlistmaker.ui.main.MainScreen
import com.example.playlistmaker.ui.search.DetailsScreen
import com.example.playlistmaker.ui.search.SearchScreen
import com.example.playlistmaker.ui.setting.SettingsScreen
import com.example.playlistmaker.ui.view_model.SearchViewModel
import com.google.gson.Gson

@Composable
fun PlaylistHost(
    modifier: Modifier = Modifier,
    startDestination: String = "main",
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = "main") {
            MainScreen(
                navigateToSearch = { navController.navigate("search") },
                navigateToSettings = { navController.navigate("setting") }
            )
        }

        composable(route = "search") {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = gson.toJson(track)
                    navController.navigate("details/$trackJson")
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(route = "details/{trackJson}") { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val track = gson.fromJson(trackJson, Track::class.java)
                DetailsScreen(track = track)
            }
        }

        composable(route = "setting") {
            SettingsScreen(
                modifier = Modifier,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}