package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import sam.compose.cocktailworldjetpackcompose.dtos.Drink
import sam.compose.cocktailworldjetpackcompose.dtos.Recipe

data class RecipesState(
    var recipes: List<Drink> = emptyList(),
    var loading: Boolean = false,
    var error: String = ""

)
