package com.githubrepo.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.usecases.TrendingRepositoryUseCase
import com.githubrepo.intent.DataIntent
import com.githubrepo.state.DataState
import com.githubrepo.state.QuestionResultState
import com.githubrepo.state.TimerState
import com.githubrepo.usecase.TimerUseCase
import com.githubrepo.viewmodel.BaseViewModel
import com.iiab.mobilebanking.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject


@HiltViewModel
class TrendingQuestionsViewModel @Inject constructor(private val trendingRepositoryUseCase: TrendingRepositoryUseCase): BaseViewModel() {

  /*  var state by mutableStateOf(DataState)
        private set*/

    private val _dataState  = MutableStateFlow<DataState>(DataState.Inactive)
    val dataState: StateFlow<DataState> = _dataState.asStateFlow() // use for compose ui else use stateflow for xml
   // val timerState: StateFlow<TimerState> = _dataState.asStateFlow() // use for compose ui else use stateflow for xml
    private val _resultState  = MutableStateFlow(listOf( QuestionResultState()))
    val resultState: StateFlow<List<QuestionResultState>> = _resultState.asStateFlow() // use for compose ui else use stateflow for xml


    private val timerIntent = TimerUseCase(viewModelScope)
    val timerStateFlow: StateFlow<TimerState> = timerIntent.timerStateFlow

    val totalCorrectAnswers = mutableListOf<Boolean>()
    val resultStateList = mutableListOf<QuestionResultState>()

    fun createCategoryMap() = mapOf("General knowledge" to "9" , "Politics" to "24",  "Mathematics" to "19" , "History" to "23", "Sports" to "21")

    fun onEventChange(event: DataIntent) {
        Log.i("Question","onEventChange event"+event)
        when (event) {
            is DataIntent.FetchReoistory -> {
            }
            is DataIntent.FetchDevelopers -> {
            }

            is DataIntent.FetchQuizzQuestions -> {
                fetchQuizzQuestions(event.amount, event.category, event.level.lowercase())
            }
            is DataIntent.UpdateAnswerList -> {
                // totalCorrectAnswers.add(event.selectedAnswer)
                Log.i("Question","onEventChange size now"+ resultStateList.size)
                resultStateList.add(event.resultState)
                _resultState.value = resultStateList

            }
            is DataIntent.GetTotalScore -> {
                val score = resultStateList.filter { state ->
                    state.isSelectedAnswerCorrect
                }.size
                //val score = totalCorrectAnswers.filter { it }.size
              //  Log.i("Question","onEventChange score"+score)
                _dataState.value = DataState.FinalScore(score)
            }
        }
    }

    fun startTimer() {
        timerIntent.toggleTime(30)
    }

    private fun fetchQuizzQuestions(size : String, category : String, level: String) {
        _dataState.value = DataState.Loading(loading = true)

        trendingRepositoryUseCase.loadQuizzQuestions(size,category, level).track {
            _dataState.value = DataState.Loading(loading = false)
            _dataState.value = when (it) {
                is NetworkResult.Success -> {
                    DataState.Success(it.data)
                }
                is NetworkResult.Error -> DataState.Error(it.error?.errorMessage ?: "")
                else -> DataState.Inactive
            }
        }
    }

    fun clearStateList(){
       resultStateList.clear()
        _resultState.value = emptyList()
    }
}