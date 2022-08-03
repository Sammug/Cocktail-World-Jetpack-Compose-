package sam.compose.cocktailworldjetpackcompose.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import sam.compose.cocktailworldjetpackcompose.utils.API_KEY
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private lateinit var retrofit: Retrofit

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

    private val okkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    fun getApiService(): ApiService{

        if(!this::retrofit.isInitialized){
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okkHttpClient)
            .build()
        }
        return retrofit.create(ApiService::class.java)
    }
}