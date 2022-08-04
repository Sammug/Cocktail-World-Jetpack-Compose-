package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.LatestDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.PopularDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.TopTenDrinksItemLayout

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val popularRecipesState = viewModel.popularRecipesState.value
    val latestRecipesState = viewModel.latestRecipesState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(text = "Most Popular",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
            )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement =  Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 16.dp),
            reverseLayout = false
        ){
            if(popularRecipesState.recipes.isNotEmpty()){
                items(popularRecipesState.recipes){recipe ->
                    val recipeName = recipe.strDrink
                    val recipeCategory = recipe.strCategory
                    val recipeThumbNail = recipe.strDrinkThumb
                    PopularDrinksItemLayout(
                        drinkName = recipeName,
                        drinkCategory = recipeCategory ?: "Cock Tail",
                        drinkThumbNail = recipeThumbNail ?: "",
                        onclick = {

                        }
                    )
                }
            }
        }

        Text(text = "Latest Recipes",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        )

        //
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical =  8.dp),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            reverseLayout = false
        ){
            if(latestRecipesState.recipes.isNotEmpty()){
                items(latestRecipesState.recipes){recipe ->
                    val recipeName = recipe.strDrink
                    val recipeCategory = recipe.strCategory
                    val recipeThumbNail = recipe.strDrinkThumb
                    LatestDrinksItemLayout(
                        drinkName = recipeName,
                        drinkCategory = recipeCategory ?: "Cock Tail",
                        drinkThumbNail = recipeThumbNail ?: "",
                        onclick = {

                        }
                    )
                }
            }
        }
    }
}