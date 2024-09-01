package com.example.stylehub.navigation

import SignupScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.stylehub.screens.login.LoginScreen
import com.example.stylehub.screens.profile.yourProfile.YourProfileScreen

@Composable
fun RootNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavSubGraph.AuthScreen) {

        navigation<NavSubGraph.AuthScreen>(startDestination = ScreenRoutes.Login) {


            composable<ScreenRoutes.Login> {
                LoginScreen(navController = navController)
            }
            composable<ScreenRoutes.SignUp> {
                SignupScreen(navController = navController)
            }
            composable<ScreenRoutes.MainScreen> {
                MainScreen(navController = navController, onLogout = {
                    navController.navigate(ScreenRoutes.Login)
                }, onProfileCLicked = { navController.navigate(ScreenRoutes.YourProfile) })

            }
            composable<ScreenRoutes.YourProfile> {
                YourProfileScreen(navController = navController)

            }


        }


    }
}
