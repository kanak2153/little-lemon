package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController: NavController, database: AppDatabase) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { Home(navController) }
        composable("Profile") { Profile(navController) }
    }
}
