package com.khaled.thmanyah.hilt

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.khaled.thmanyah.core.utils.AppConstants
import com.khaled.thmanyah.core.utils.AppData
import com.khaled.thmanyah.core.utils.HostSelectionInterceptor
import com.khaled.thmanyah.data.remote.api.HomeApiService
import com.khaled.thmanyah.data.remote.api.SearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = AppData.BASE_URL
    private const val TIMEOUT = 5 * 60 * 1000L


    @Provides
    @Singleton
    fun provideOkHttpClient(
        hostSelectionInterceptor: HostSelectionInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().retryOnConnectionFailure(true)
            .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS).readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                    .header(AppConstants.TYPE_ACCEPT_JSON, AppConstants.TYPE_APP_JSON)
                    .header(AppConstants.TYPE_ACCEPT_LANGUAGE_JSON, AppConstants.EN_KEY)
                return@Interceptor chain.proceed(builder.build())
            }).addInterceptor(hostSelectionInterceptor).build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        prettyPrint = true
        encodeDefaults = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory(AppConstants.MEDIA_TYPE_APP_JSON)

    @Provides
    @Singleton
    fun provideHostSelectionInterceptor(): HostSelectionInterceptor {
        return HostSelectionInterceptor()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient, converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(converterFactory)
            .client(client).build()
    }

    @Provides
    @Singleton
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)

    @Provides
    @Singleton
    fun provideSearchApiService(retrofit: Retrofit): SearchApiService =
        retrofit.create(SearchApiService::class.java)
}
