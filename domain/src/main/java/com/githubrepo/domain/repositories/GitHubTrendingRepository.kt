package com.githubrepo.domain.repositories

import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.domain.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow

interface GitHubTrendingRepository : BaseRepository {

    fun getTrendingRepositories(language: String , since : String): Flow<NetworkResult<List<RepositoriesEntity.RepositoryDetails>>>

    fun getTrendingDevelopers(language: String , since : String): Flow<NetworkResult<RepositoriesEntity.DevelopersDetails>>

    fun fetchQuestionsForCategory(amount: String , category : String, level: String): Flow<NetworkResult<RepositoriesEntity.QuizzDetails>>

}