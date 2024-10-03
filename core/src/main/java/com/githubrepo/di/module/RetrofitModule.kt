package com.githubrepo.di.module

import com.githubrepo.config.Constants
import com.githubrepo.remote.apis.GitHubTrendingApis
import com.githubrepo.domain.repositories.GitHubTrendingRepository
import com.githubrepo.repositoryimpl.GitHubTrendingRepositoryImpl
import com.githubrepo.domain.usecases.TrendingRepositoryUseCase
import com.githubrepo.usecasesimpl.TrendingRepositoryUseCaseImpl
import com.githubrepo.utils.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl() : String{
        return Constants.BASE_URL_QUIZZ
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String, okhttpclient: OkHttpClient, gson: Gson) : Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).client(okhttpclient).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideOkHttpClientInstance() : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
     fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(Constants.OAuth.ACCESS_TOKEN)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideTrendingApiService(retrofit: Retrofit): GitHubTrendingApis =
        retrofit.create(GitHubTrendingApis::class.java)

    @Provides
    fun providesUseCaseImpl(apiServices: GitHubTrendingRepository): TrendingRepositoryUseCase =
        TrendingRepositoryUseCaseImpl(apiServices)

    @Provides
    fun providesApiRepository(apiServices: GitHubTrendingApis): GitHubTrendingRepository =
        GitHubTrendingRepositoryImpl(apiServices)
    /* @Provides
     @Singleton
     fun provideApiService(retrofit: Retrofit): MyApiService =
         retrofit.create(MyApiService::class.java)*/
}