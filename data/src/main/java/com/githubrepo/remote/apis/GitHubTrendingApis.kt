package com.githubrepo.remote.apis

import com.githubrepo.config.ApiModuleConfig
import com.githubrepo.remote.dto.RespositoriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubTrendingApis {

    @GET("/repositories")
    suspend fun getTrendingRepositories(
        @Query("language") language: String? = null,
        @Query("since") since: String = "daily",
     ) : List<RespositoriesDto.RepositoryDetails>//NetworkResult<RespositoriesDto.RepositoryDetails>

    @GET("{module}")
    suspend fun getTrendingDevelopers(
        @Path("module") module: String = ApiModuleConfig.moduleDevelopers,
        @Query("language") language: String? = null,
        @Query("since") since: String = "daily",
    ) : RespositoriesDto.DevelopersDetails

    @GET(".")
    suspend fun fetchQuizzQuestions(
        @Query("amount") amount: String = "10",
        @Query("category") category: String? = null,
        @Query("difficulty") difficulty: String = "easy"
    ) : RespositoriesDto.QuizzDetails
}