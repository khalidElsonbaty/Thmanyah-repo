package com.khaled.thmanyah.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khaled.thmanyah.presentation.home.HomeScreen
import com.khaled.thmanyah.presentation.search.SearchScreen

@Composable
fun AppNavHost(modifier: Modifier,navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(modifier,navController) }
        composable("search") { SearchScreen(modifier,navController) }
    }
}