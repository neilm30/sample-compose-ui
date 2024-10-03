package com.githubrepo.di.module

import com.githubrepo.remote.apis.GitHubTrendingApis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

/*    @Provides
    @Singleton
    fun provideTrendingApiService(retrofit: Retrofit): GitHubTrendingApis =
        retrofit.create(GitHubTrendingApis::class.java)*/
}