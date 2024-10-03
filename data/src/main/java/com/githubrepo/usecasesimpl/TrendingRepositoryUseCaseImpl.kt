package com.githubrepo.usecasesimpl

import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.domain.repositories.GitHubTrendingRepository
import com.githubrepo.domain.usecases.TrendingRepositoryUseCase
import kotlinx.coroutines.flow.Flow

class TrendingRepositoryUseCaseImpl (private val repository: GitHubTrendingRepository) :
    TrendingRepositoryUseCase {
    override fun loadRepositories(language: String, since: String): Flow<NetworkResult<List<RepositoriesEntity.RepositoryDetails>>> {
        return repository.getTrendingRepositories(language, since)
    }

    override fun loadDevelopers(language: String, since: String): Flow<NetworkResult<RepositoriesEntity.DevelopersDetails>> {
        return repository.getTrendingDevelopers(language, since)
    }

    override fun loadQuizzQuestions(
        amount: String,
        category: String,
        level: String
    ): Flow<NetworkResult<RepositoriesEntity.QuizzDetails>> {
        return repository.fetchQuestionsForCategory(amount, category, level)
    }
}

//single<AuthUseCase> { AuthUseCaseImpl(get() as AuthRepository) }
//single<FinanceRepository> { FinanceRepositoryImpl(get() as FinanceApi) }

