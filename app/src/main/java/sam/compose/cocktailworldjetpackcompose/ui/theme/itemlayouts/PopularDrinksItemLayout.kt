package sam.compose.cocktailworldjetpackcompose.ui.theme.itemlayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sam.compose.cocktailworldjetpackcompose.R
import sam.compose.cocktailworldjetpackcompose.ui.theme.CocktailWorldJetpackComposeTheme
import sam.compose.cocktailworldjetpackcompose.ui.theme.Golden
import sam.compose.cocktailworldjetpackcompose.ui.theme.LightPurple

@Composable
fun PopularDrinksItemLayout(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkCategory: String,
    drinkThumbNail: String,
    onclick: () -> Unit
) {
    Box(modifier = Modifier
            .width(180.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable {  }
        .then(modifier)
    ) {
        Row(modifier = Modifier
            .width(180.dp)
            .height(120.dp)
            .background(color = LightPurple)
        ) {
            val imageDrawable = painterResource(id = R.drawable.drink_image)
            Image(modifier = modifier
                .size(height = 120.dp, width = 180.dp)
                .weight(1f),
                painter = imageDrawable,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Recipe Thumbnail"
            )
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .aspectRatio(1f)
//                .weight(1f)
//            ) {
//                val imageDrawable = painterResource(id = R.drawable.drink_image)
//                Image(modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(),
//                    painter = imageDrawable,
//                    contentDescription = "Recipe Thumbnail"
//                )
//            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
            ) {
                Text(
                    text = drinkName,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Golden,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = drinkCategory,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdPreview() {
    CocktailWorldJetpackComposeTheme {
        PopularDrinksItemLayout(
            drinkName = "Cocktail Horse's Neck",
            drinkCategory = "Cocktail",
            drinkThumbNail = "",
        ) {

        }
    }
}