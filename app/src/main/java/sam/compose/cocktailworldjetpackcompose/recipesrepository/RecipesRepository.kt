package sam.compose.cocktailworldjetpackcompose.recipesrepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import sam.compose.cocktailworldjetpackcompose.api.RetrofitClient
import sam.compose.cocktailworldjetpackcompose.dtos.Recipe
import sam.compose.cocktailworldjetpackcompose.utils.Resource
import java.io.IOException

class RecipesRepository(private val retrofitClient: RetrofitClient) {

    suspend fun getPopularRecipes(): Flow<Resource<Recipe>> = flow {
        try{
            val recipes = retrofitClient.getApiService().getPopularCockTails()
            emit(Resource.Success(data = recipes.body()))
        }catch(exc: HttpException){
            emit(Resource.Error(data = null, message = "Couldn't reach server, check your internet connection"))
        }catch(exc: IOException){
            emit(Resource.Error(data = null, message = exc.localizedMessage ?:"An expected error occurred"))
        }
    }

    suspend fun getLatestRecipes(): Flow<Resource<Recipe>> = flow {
        try{
            val latestRecipes = retrofitClient.getApiService().getMostLatestCockTails()
            emit(Resource.Success(data = latestRecipes.body()))
        }catch(exc: HttpException){
            emit(Resource.Error(data = null, message = "Couldn't reach server, check your internet connection"))
        }catch(exc: IOException){
            emit(Resource.Error(data = null, message = exc.localizedMessage ?:"An expected error occurred"))
        }
    }

    suspend fun getTopTenRecipes(): Flow<Resource<Recipe>> = flow {
        try{
            val topTenRecipes = retrofitClient.getApiService().getRandomTopDrinks()
            emit(Resource.Success(data = topTenRecipes.body()))
        }catch(exc: HttpException){
            emit(Resource.Error(data = null, message = "Couldn't reach server, check your internet connection"))
        }catch(exc: IOException){
            emit(Resource.Error(data = null, message = exc.localizedMessage ?:"An expected error occurred"))
        }
    }
}

