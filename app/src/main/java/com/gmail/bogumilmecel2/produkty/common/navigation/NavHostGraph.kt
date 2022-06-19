package com.gmail.bogumilmecel2.produkty.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Screen
import com.gmail.bogumilmecel2.produkty.common.util.extensions.asLifecycleAwareState
import com.gmail.bogumilmecel2.produkty.feature_items.presentation.ItemsScreen
import com.gmail.bogumilmecel2.produkty.feature_login.presentation.LoginScreen
import com.gmail.bogumilmecel2.produkty.feature_splash.presentation.SplashScreen

@Composable
fun NavHostGraph(
    navController: NavHostController = rememberNavController(),
    navigator: Navigator,
    startDestination:String = Screen.SplashScreen.route
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val navigatorState by navigator.navActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )
    LaunchedEffect(navigatorState) {
        navigatorState?.let {
            it.parcelableArguments.forEach { arg ->
                navController.currentBackStackEntry?.arguments?.putParcelable(arg.key, arg.value)
            }
            navController.navigate(it.destination, it.navOptions)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.LoginScreen.route){
            LoginScreen()
        }
        composable(route = Screen.ItemsScreen.route){
            ItemsScreen()
        }
        composable(route = Screen.SplashScreen.route){
            SplashScreen()
        }
    }
}