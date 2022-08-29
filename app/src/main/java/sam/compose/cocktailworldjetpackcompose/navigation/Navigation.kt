@file:OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)

package sam.compose.cocktailworldjetpackcompose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import sam.compose.cocktailworldjetpackcompose.ui.theme.screens.HomeScreen
import sam.compose.cocktailworldjetpackcompose.ui.theme.screens.RecipeDetailsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route,
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ){
            val recipeId = it.arguments?.get("id")
            if (recipeId != null){
                RecipeDetailsScreen(navController = navController, recipeId = recipeId.toString())
            }
        }
    }
    
}