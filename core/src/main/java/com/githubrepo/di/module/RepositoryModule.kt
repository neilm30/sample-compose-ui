package com.githubrepo.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

/*
    @Binds
    abstract fun bindMyDependencyRepo(apiHelper: GitHubTrendingRepositoryImpl) : GitHubTrendingRepository
*/

   /* @Binds
    abstract fun bindMyDependencyUsecase(apiHelper: TrendingRepositoryUseCaseImpl) : TrendingRepositoryUseCase*/

}