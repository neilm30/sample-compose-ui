package com.githubrepo.state

data class QuestionResultState(
    val selectedIndex: Int = 0,
    val selectedAnswer: String = "",
    val isSelectedAnswerCorrect: Boolean = false
) {

}
