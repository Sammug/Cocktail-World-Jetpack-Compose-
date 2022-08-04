package sam.compose.cocktailworldjetpackcompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sam.compose.cocktailworldjetpackcompose.api.ApiService
import sam.compose.cocktailworldjetpackcompose.utils.API_KEY
import sam.compose.cocktailworldjetpackcompose.utils.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val okHttpClient =  OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        if (sam.compose.cocktailworldjetpackcompose.BuildConfig.DEBUG){
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private val interceptor = Interceptor{chain: Interceptor.Chain ->
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
            .addHeader("x-rapidapi-key", API_KEY)
            .build()
        chain.proceed(request)
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}