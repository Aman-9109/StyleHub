package com.example.stylehub.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stylehub.screens.category.CategoryScreen
import com.example.stylehub.screens.home.HomeScreen
import com.example.stylehub.screens.profile.ProfileScreen
import com.example.stylehub.screens.trending.TrendingScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController,onLogout: () -> Unit,onProfileCLicked: () -> Unit) {

    val navController = rememberNavController()


    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            unSelectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            route = ScreenRoutes.home
        ),
        BottomNavigationItem(
            title = "Category",
            unSelectedIcon = Icons.Outlined.Category,
            selectedIcon = Icons.Filled.Category,
            route = ScreenRoutes.category
        ),
        BottomNavigationItem(
            title = "Trending",
            unSelectedIcon = Icons.AutoMirrored.Outlined.TrendingUp,
            selectedIcon = Icons.AutoMirrored.Filled.TrendingUp,
            route = ScreenRoutes.trending
        ), BottomNavigationItem(
            title = "Profile",
            unSelectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            route = ScreenRoutes.profile
        )
    ) // Bottom MainScreen items
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar(containerColor = Color.White, modifier = Modifier.height(110.dp)) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index
                        navController.navigate(item.route)
                    },
                    icon = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = if (selectedItemIndex == index) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                }, contentDescription = ""
                            )
                            Text(text = item.title)
                        }

                    })

            }
        }
    }) { padding ->


        Column(
            modifier = Modifier
                .padding()
                .fillMaxWidth()
        ) {
            NavHost(navController = navController, startDestination = ScreenRoutes.home) {


                composable<ScreenRoutes.home> {
                    HomeScreen(navController = navController, paddingValues = padding)

                }
                composable<ScreenRoutes.category> {
                    CategoryScreen(navController = navController)

                }
                composable<ScreenRoutes.trending> {
                    TrendingScreen(navController = navController)

                }
                composable<ScreenRoutes.profile> {
                    ProfileScreen(navController = navController, paddingValues = padding, onLogout = onLogout, onProfileCLicked = onProfileCLicked)

                }



            }
        }

    }

}


data class BottomNavigationItem(


    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: ScreenRoutes


)