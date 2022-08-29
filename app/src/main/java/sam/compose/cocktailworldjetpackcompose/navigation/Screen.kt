package sam.compose.cocktailworldjetpackcompose.navigation

sealed class Screen(
    val route: String
){
    object HomeScreen: Screen(route = "homeScreen")
    object DetailsScreen: Screen(route = "details/{id}")
}
