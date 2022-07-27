package sam.compose.cocktailworldjetpackcompose.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sam.compose.cocktailworldjetpackcompose.dtos.Recipe

interface ApiService {
    @GET("popular.php")
    suspend fun getPopularCockTails(): Response<Recipe>

    @GET("latest.php")
    suspend fun getMostLatestCockTails(): Response<Recipe>

    @GET("randomselection.php")
    suspend fun getRandomTopDrinks(): Response<Recipe>

    @GET("lookup.php")
    suspend fun getDrinkDetails(
        @Query("i") drinkId: String
    ): Response<Recipe>

    @GET("popular.php")
    suspend fun getPopularCockTails(
        @Query("loaded_page_items") loaded_items_per_page: Int?,
        @Query("page") page: Int?
    ): Recipe
}