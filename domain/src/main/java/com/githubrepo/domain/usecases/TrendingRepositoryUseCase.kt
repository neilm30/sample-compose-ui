package com.githubrepo.domain.usecases

import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.entity.RepositoriesEntity
import kotlinx.coroutines.flow.Flow

interface TrendingRepositoryUseCase {

    fun loadRepositories(language: String, since: String): Flow<NetworkResult<List<RepositoriesEntity.RepositoryDetails>>>
    fun getTrendingRepositories(language: String , since : String): Flow<NetworkResult<List<RepositoriesEntity.RepositoryDetails>>>

    fun loadDevelopers(language: String, since: String): Flow<NetworkResult<RepositoriesEntity.DevelopersDetails>>
    fun loadQuizzQuestions(amount: String, category: String, level: String): Flow<NetworkResult<RepositoriesEntity.QuizzDetails>>

}