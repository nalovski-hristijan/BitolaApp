package com.hnalovski.bitolaapp.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hnalovski.bitolaapp.BitolaViewModel
import com.hnalovski.bitolaapp.screens.CategoryScreen
import com.hnalovski.bitolaapp.screens.DetailScreen
import com.hnalovski.bitolaapp.screens.RecommendationScreen

@Composable
fun BitolaNavigation(windowSize: WindowWidthSizeClass) {
    val viewModel: BitolaViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BitolaScreens.CATEGORY.route) {
        composable(route = BitolaScreens.CATEGORY.route) {
            CategoryScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = BitolaScreens.RECOMMENDATION.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            RecommendationScreen(
                viewModel = viewModel,
                navController = navController,
                categoryId = categoryId!!,
                windowSize = windowSize
            )
        }
        composable(
            route = BitolaScreens.DETAIL.route,
            arguments = listOf(navArgument("detailId") { type = NavType.IntType })
        ) { backStackEntry ->
            val detailId = backStackEntry.arguments?.getInt("detailId")
            DetailScreen(viewModel = viewModel,navController = navController, detailId =  detailId!!)
        }
    }
}