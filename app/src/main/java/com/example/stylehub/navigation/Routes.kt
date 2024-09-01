package com.example.stylehub.navigation

import kotlinx.serialization.Serializable


sealed class NavSubGraph {
    @Serializable
    data object AuthScreen:NavSubGraph()

    @Serializable
    data object MainScreen:NavSubGraph()

    @Serializable
    data object ProfileSubgraph:NavSubGraph()
}

sealed class ScreenRoutes {


    @Serializable
    object MainScreen : ScreenRoutes()

    @Serializable
    object home : ScreenRoutes()

    @Serializable
    object category : ScreenRoutes()

    @Serializable
    object trending : ScreenRoutes()

    @Serializable
    object profile : ScreenRoutes()

    @Serializable
    object Login : ScreenRoutes()

    @Serializable
    object SignUp : ScreenRoutes()

    @Serializable
    object YourProfile : ScreenRoutes()

}