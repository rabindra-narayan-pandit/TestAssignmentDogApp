package com.rabindra.testassignmentdogapp.presentation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rabindra.testassignmentdogapp.presentation.screen.BrowseScreen
import com.rabindra.testassignmentdogapp.presentation.screen.CalendarScreen
import com.rabindra.testassignmentdogapp.presentation.screen.CollectionsScreen
import com.rabindra.testassignmentdogapp.presentation.screen.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DogApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        DogAppNavHost(navController = navController)
    }
}

@Composable
fun DogAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("browse") { BrowseScreen() }
        composable("collections") { CollectionsScreen() }
        composable("calendar") { CalendarScreen() }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", Icons.Filled.Home, "Home"),
        BottomNavItem("browse", Icons.Filled.Search, "Browse"),
        BottomNavItem("collections", Icons.Filled.Favorite, "Collections"),
        BottomNavItem("calendar", Icons.Filled.CalendarToday, "Calendar")
    )

    val currentRoute by navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)