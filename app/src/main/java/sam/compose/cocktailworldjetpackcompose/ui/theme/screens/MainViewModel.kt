package sam.compose.cocktailworldjetpackcompose.ui.theme.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import sam.compose.cocktailworldjetpackcompose.recipesrepository.RecipesRepository
import sam.compose.cocktailworldjetpackcompose.utils.Resource

class MainViewModel(private val recipesRepository: RecipesRepository): ViewModel() {
    private var _popularRecipesState = mutableStateOf(RecipesState())
    val popularRecipesState = _popularRecipesState

    private var _latestRecipesState = mutableStateOf(RecipesState())
    val latestRecipesState = _latestRecipesState

    private var _topTenRecipesState = mutableStateOf(RecipesState())
    val topTenRecipesState = _topTenRecipesState

    init{
        getAllPopularRecipes()
        getMostLatestRecipes()
        getTopTenRecipes()
    }

    private fun getAllPopularRecipes() = viewModelScope.launch {
        recipesRepository.getPopularRecipes().onEach {resource ->
            when(resource){
                is Resource.Success -> {
                    _popularRecipesState.value = RecipesState(recipes = resource.data?.drinks!!)
                }
                is Resource.Error -> {
                    _popularRecipesState.value = RecipesState(error = resource.message ?: "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _popularRecipesState.value = RecipesState(loading = true)
                }
            }
        }
    }

    private fun getMostLatestRecipes() = viewModelScope.launch {
        recipesRepository.getPopularRecipes().onEach {resource ->
            when(resource){
                is Resource.Success -> {
                    _latestRecipesState.value = RecipesState(recipes = resource.data?.drinks!!)
                }
                is Resource.Error -> {
                    _latestRecipesState.value = RecipesState(error = resource.message ?: "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _latestRecipesState.value = RecipesState(loading = true)
                }
            }
        }
    }

    private fun getTopTenRecipes() = viewModelScope.launch {
        recipesRepository.getPopularRecipes().onEach {resource ->
            when(resource){
                is Resource.Success -> {
                    _topTenRecipesState.value = RecipesState(recipes = resource.data?.drinks!!)
                }
                is Resource.Error -> {
                    _topTenRecipesState.value = RecipesState(error = resource.message ?: "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _topTenRecipesState.value = RecipesState(loading = true)
                }
            }
        }
    }
}