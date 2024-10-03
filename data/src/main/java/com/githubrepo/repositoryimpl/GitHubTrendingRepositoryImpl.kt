package com.githubrepo.repositoryimpl

import android.util.Log
import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.mappers.dtotoentity.toDeveloperDetails
import com.githubrepo.mappers.dtotoentity.toRepositoryDetails
import com.githubrepo.remote.apis.GitHubTrendingApis
import com.githubrepo.domain.repositories.GitHubTrendingRepository
import com.githubrepo.mappers.dtotoentity.toCategoryDetails
import com.githubrepo.mappers.dtotoentity.toQuizzDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GitHubTrendingRepositoryImpl (val api: GitHubTrendingApis) : BaseRepositoryImpl(),
    GitHubTrendingRepository {

    override fun getTrendingRepositories(
        language: String,
        since: String
    ): Flow<NetworkResult<List<RepositoriesEntity.RepositoryDetails>>> =
        flow {
          emit(
              apiCall {
                      api.getTrendingRepositories(language = language, since = since)
                          .toRepositoryDetails()
              }
                  )
        }.flowOn(Dispatchers.IO)

    override fun getTrendingDevelopers(
        language: String,
        since: String
    ): Flow<NetworkResult<RepositoriesEntity.DevelopersDetails>> =
        flow {
           emit(
               apiCall { api.getTrendingDevelopers(language = language, since = since).toDeveloperDetails()}
            )
        }.flowOn(Dispatchers.IO)

    override fun fetchQuestionsForCategory(
        amount: String,
        category: String,
        level: String
    ): Flow<NetworkResult<RepositoriesEntity.QuizzDetails>> =
        flow {
            emit(
                apiCall {
                    Log.i("Question","onEventChange quizz")

                    api.fetchQuizzQuestions(amount, category= category, difficulty = level).toQuizzDetails()
                }
            )
        }.flowOn(Dispatchers.IO)

}