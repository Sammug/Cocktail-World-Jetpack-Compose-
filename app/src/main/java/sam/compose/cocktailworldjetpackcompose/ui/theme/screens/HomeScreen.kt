package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.LatestDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.PopularDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.TopTenDrinksItemLayout

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    popularRecipesState: RecipesState,
    latestRecipesState: RecipesState,
    topTenRecipesState: RecipesState
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ){
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement =  Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp),
                reverseLayout = false
            ) {
                if(topTenRecipesState.recipes.isNotEmpty()){
                    topTenRecipesState.recipes.forEach { recipe ->
                        val recipeName = recipe.strDrink
                        val recipeCategory = recipe.strCategory
                        val recipeThumbNail = recipe.strDrinkThumb
                        item {
                            TopTenDrinksItemLayout(
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
        item {
            Text(text = "Most Popular",
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement =  Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp),
                reverseLayout = false
            ){
                if(popularRecipesState.recipes.isNotEmpty()){
                    popularRecipesState.recipes.forEach { recipe ->
                        val recipeName = recipe.strDrink
                        val recipeCategory = recipe.strCategory
                        val recipeThumbNail = recipe.strDrinkThumb
                        item {
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
            }
        }

        item {
            Text(text = "Latest Recipes",
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
            )
        }

        item {
            //val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
            FlowRow(
                modifier = Modifier
                    .padding(PaddingValues(horizontal = 8.dp, vertical = 8.dp)),
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 8.dp,
                mainAxisAlignment = MainAxisAlignment.SpaceEvenly
            ) {
                if(latestRecipesState.recipes.isNotEmpty()){
                    latestRecipesState.recipes.forEach{ recipe ->
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
}