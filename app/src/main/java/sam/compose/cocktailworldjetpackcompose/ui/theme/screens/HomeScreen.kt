@file:OptIn(ExperimentalPagerApi::class)

package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.LatestDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.PopularDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts.TopTenDrinksItemLayout
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.Golden
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.White

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: MainViewModel = hiltViewModel()
    val popularRecipesState: RecipesState = viewModel.popularRecipesState.value
    val latestRecipesState: RecipesState = viewModel.latestRecipesState.value
    val topTenRecipesState: RecipesState = viewModel.topTenRecipesState.value
    val pagerState = rememberPagerState()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ){
        item {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
            ){
                HorizontalPager(
                    count = if (topTenRecipesState.recipes.isNotEmpty()){
                        topTenRecipesState.recipes.size
                    } else 10 ,
                    reverseLayout = false,
                    itemSpacing = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(start = 4.dp, end = 4.dp, top = 4.dp),
                    state = pagerState
                ) {
                    if(topTenRecipesState.recipes.isNotEmpty()){
                        val drink = topTenRecipesState.recipes[it]
                        val recipeName = drink.strDrink
                        val recipeCategory = drink.strCategory
                        val recipeThumbNail = drink.strDrinkThumb

                        TopTenDrinksItemLayout(
                            drinkName = recipeName,
                            drinkCategory = recipeCategory ?: "Cock Tail",
                            drinkThumbNail = recipeThumbNail ?: "",
                            onclick = {
                                navController.navigate(route = "details/${drink.idDrink}"
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                //pager indicators
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(20.dp),
                    activeColor = Golden,
                    inactiveColor = Color.DarkGray
                )
                // end
            }
        }

        item {
            Text(text = "Most Popular",
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                color = White
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
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
                                    navController.navigate(route = "details/${recipe.idDrink}")
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
                fontSize = 16.sp,
                color = White
            )
        }

        item {
            val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2.15f)
            FlowRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 8.dp,
                mainAxisSpacing = 8.dp,
                mainAxisAlignment = MainAxisAlignment.SpaceBetween
            ) {
                if(latestRecipesState.recipes.isNotEmpty()){
                    latestRecipesState.recipes.forEach{ recipe ->
                        val recipeName = recipe.strDrink
                        val recipeCategory = recipe.strCategory
                        val recipeThumbNail = recipe.strDrinkThumb
                        LatestDrinksItemLayout(
                            modifier = Modifier.width(itemSize),
                            drinkName = recipeName,
                            drinkCategory = recipeCategory ?: "Cock Tail",
                            drinkThumbNail = recipeThumbNail ?: "",
                            onclick = {
                                navController.navigate(route = "details/${recipe.idDrink}")
                            }
                        )
                    }
                }
            }
        }
    }
}