package sam.compose.cocktailworldjetpackcompose.dtos

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strTags: String?,
    val strCategory: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?
)
