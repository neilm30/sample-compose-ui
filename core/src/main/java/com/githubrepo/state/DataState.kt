package com.githubrepo.state

import com.githubrepo.domain.entity.RepositoriesEntity

sealed class DataState {
    object Inactive : DataState()
    data class Loading(val loading: Boolean = false) : DataState()
  //  class Success<T : Any>(val data : T) : DataState()
  //  class Success(val data: List<RepositoriesEntity.RepositoryDetails>) : DataState()
    class Success(val data: RepositoriesEntity.QuizzDetails) : DataState()
    data class FinalScore(val score : Int) : DataState()
    class Error(val e : String) : DataState()
}