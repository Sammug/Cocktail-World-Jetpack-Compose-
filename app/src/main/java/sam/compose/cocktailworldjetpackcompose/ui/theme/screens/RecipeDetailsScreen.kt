package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import sam.compose.cocktailworldjetpackcompose.R
import sam.compose.cocktailworldjetpackcompose.dtos.Drink
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.CocktailWorldJetpackComposeTheme
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.Dark
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.LightPurple
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.White

@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    recipeId: String
) {
    val viewModel: MainViewModel = hiltViewModel()
    val recipeState: RecipesState = viewModel.recipeInfoState.value
    var recipeType: String
    var recipeCategory : String
    var recipeThumbNail: String
    var recipeName: String

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Dark)
    ){

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item {
                Row(
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavBackIcon()
                    if (recipeState.recipes.isNotEmpty()){
                        recipeState.recipes.forEach {recipe ->
                            recipeName = recipe.strDrink

                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = recipeName,
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                color = White
                            )
                        }
                    }
                }
            }

            item {
                if (recipeState.recipes.isNotEmpty()){
                    recipeState.recipes.forEach {recipe ->
                        recipeName = recipe.strDrink
                        recipeType = recipe.strAlcoholic.toString()
                        recipeCategory = recipe.strCategory.toString()
                        recipeThumbNail = recipe.strDrinkThumb.toString()
                        RecipeImageCard(
                            drinkThumbNail = recipeThumbNail,
                            category = recipeCategory,
                            type = recipeType
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavBackIcon() {
    Card(
        modifier = Modifier
            .width(42.dp)
            .height(44.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = LightPurple,
        elevation = 2.dp
    ) {
        val backIcon: Painter = painterResource(id = R.drawable.arrow_back_icon)
        Image(
            painter = backIcon,
            contentDescription = "Back Icon",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
@Composable
fun OutlinedFavouriteIcon() {
    Card(
        modifier = Modifier
            .width(64.dp)
            .height(64.dp),
        shape = RoundedCornerShape(64.dp),
        backgroundColor = LightPurple,
        elevation = 2.dp
    ) {
        val backIcon = painterResource(id = R.drawable.favourite_icon)
        Image(
            modifier = Modifier.padding(8.dp),
            painter = backIcon, contentDescription = "Back Icon")
    }
}
@Composable
fun FilledFavouriteIcon() {
    Card(
        modifier = Modifier
            .width(64.dp)
            .height(64.dp),
        shape = RoundedCornerShape(64.dp),
        backgroundColor = LightPurple,
        elevation = 2.dp
    ) {
        val backIcon = painterResource(id = R.drawable.filled_fav_icon)
        Image(
            modifier = Modifier.padding(8.dp),
            painter = backIcon, contentDescription = "Back Icon")
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RecipeImageCard(
    drinkThumbNail: String,
    category: String,
    type: String
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ){
        val imagePainter = rememberImagePainter(
            data = drinkThumbNail,
            builder = {
                placeholder(R.drawable.drink_image)
            }
        )
        Image(
            painter = imagePainter, contentDescription = "Drink Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(8.dp))
                .weight(2f)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = LightPurple,
            elevation = 2.dp
        ) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Category",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = category
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Type",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = type
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    CocktailWorldJetpackComposeTheme {
//        RecipeDetailsScreen(navController = rememberNavController(), title = "Martini")
        val imagePainter = rememberImagePainter(
            data = "drinkThumbNail",
            builder = {
                placeholder(R.drawable.drink_image)
            }
        )
        RecipeImageCard(drinkThumbNail = "", category = "Shot", type = "Alcoholic")
    }
}