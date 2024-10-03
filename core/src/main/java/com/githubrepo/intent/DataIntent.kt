package com.githubrepo.intent

import com.githubrepo.state.QuestionResultState

sealed class DataIntent {
    object FetchReoistory : DataIntent()
    object FetchDevelopers : DataIntent()
    data class UpdateAnswerList(val resultState : QuestionResultState) : DataIntent()
    object GetTotalScore : DataIntent()

    data class FetchQuizzQuestions(val amount: String,val category: String,val level: String) : DataIntent()
}
