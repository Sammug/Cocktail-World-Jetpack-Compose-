@file:OptIn(ExperimentalCoilApi::class)

package sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import sam.compose.cocktailworldjetpackcompose.R
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.CocktailWorldJetpackComposeTheme
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.Golden
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.Grey
import sam.compose.cocktailworldjetpackcompose.ui.theme.styles.LightPurple

@Composable
fun LatestDrinksItemLayout(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkCategory: String,
    textPadding: Dp = 8.dp,
    drinkThumbNail: String,
    onclick: () -> Unit
) {
    Card(backgroundColor = Grey) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { }
                .then(modifier)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Grey)
            ) {
                //val imageDrawable = painterResource(id = R.drawable.drink_image)
                val imagePainter = rememberImagePainter(
                    data = drinkThumbNail,
                    builder = {
                        placeholder(R.drawable.drink_image)
                    }
                )
                val painterState = imagePainter.state
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.FillBounds,
                    painter = imagePainter, contentDescription = "Recipe Thumbnail")
                if (painterState is ImagePainter.State.Loading){
                    CircularProgressIndicator()
                }

                Text(
                    text = drinkName,
                    style = TextStyle(
                        color = Golden,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = modifier.padding(textPadding)
                )
                Text(
                    text = drinkCategory,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = modifier.padding(horizontal = textPadding)
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CocktailWorldJetpackComposeTheme {
        LatestDrinksItemLayout(
            drinkName = "Cocktail Horse's Neck",
            drinkCategory = "Cocktail",
            drinkThumbNail = "",
            textPadding = 8.dp
        ) {

        }
    }
}