package com.githubrepo.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.usecases.TrendingRepositoryUseCase
import com.githubrepo.intent.DataIntent
import com.githubrepo.state.DataState
import com.githubrepo.viewmodel.BaseViewModel
import com.iiab.mobilebanking.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject


@HiltViewModel
class TrendingRepositoryViewModel @Inject constructor(private val trendingRepositoryUseCase: TrendingRepositoryUseCase): BaseViewModel() {

    private val _dataState  = mutableStateOf<DataState>(DataState.Inactive)
    val dataState: State<DataState> = _dataState // use for compose ui else use stateflow for xml
  //  var items: ObservableList<RepositoriesEntity.RepositoryDetails> = ObservableArrayList()
    var items: ObservableList<String> = ObservableArrayList()

    val userIntent = Channel<DataIntent>(Channel.UNLIMITED)
    init {
        handleIntent(DataIntent.FetchReoistory)
    }

    //val itemBinding: ItemBinding<String> = ItemBinding.of<String>(BR.itemViewModel, com.iiab.mobilebanking.R.layout.item_repository_details)


    val itemBinding: OnItemBindClass<String> =
        OnItemBindClass<String>()
            .map(String::class.java) { itemBinding, position, _ ->
                itemBinding.set(BR.itemViewModel, com.iiab.mobilebanking.R.layout.item_repository_details)
            }
    var itemAdapter = BindingRecyclerViewAdapter<String>()

     fun handleIntent(fetchReoistory: DataIntent.FetchReoistory) {
        userIntent.consumeAsFlow().track {
            fetchTrendingRepos()
        }
      userIntent.consumeAsFlow().track {
          when(it){
               is DataIntent.FetchReoistory -> fetchTrendingRepos()
               is DataIntent.FetchDevelopers -> fetchTrendingDevelopers()
              else -> TODO()
          }
      }
    }

   private fun fetchTrendingRepos() {
        _dataState.value = DataState.Loading(true)
        trendingRepositoryUseCase.loadRepositories("en", "daily").track {
            _dataState.value = DataState.Inactive
           /* _dataState.value = when (it) {
                is NetworkResult.Success -> DataState.Success(it.data)
                is NetworkResult.Error -> DataState.Error(it.error?.errorMessage ?: "")
                else -> DataState.Inactive
            }*/
        }
    }


    private fun fetchTrendingDevelopers() {
        _dataState.value = DataState.Loading(false)
        trendingRepositoryUseCase.loadDevelopers("en", "daily").track {
            _dataState.value = DataState.Inactive
          /*  _dataState.value = when (it) {
                is NetworkResult.Success -> DataState.Success(it)
                is NetworkResult.Error -> DataState.Error(it.error?.errorMessage ?: "")
                else -> DataState.Inactive
            }*/
        }
    }
}