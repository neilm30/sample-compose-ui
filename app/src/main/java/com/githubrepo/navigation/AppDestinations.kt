package com.githubrepo.navigation

import com.githubrepo.domain.entity.RepositoriesEntity
import kotlinx.serialization.Serializable

/*object AppDestinations {
    const val HOME_SCREEN = "home_screen"
    const val DETAIL_SCREEB = "detail_screen"
    const val LOGIN_SCREEN = "login_screen"
    const val QUESTION_SCREEN = "question_screen"
}*/

/*sealed class AppDestinations(val route: String) {
    object HOME_SCREEN : AppDestinations("home")
    object LOGIN_SCREEN : AppDestinations("login")
    object DETAIL_SCREEB : AppDestinations("details")
    data class QUESTION_SCREEN(val result : RepositoriesEntity.QuizzDetails) : AppDestinations("quizscreen")
}*/

@Serializable
object LOGIN_SCREEN

@Serializable
object SCORE_SCREEN

@Serializable
data class QUESTION_SCREEN(val result : RepositoriesEntity.QuizzDetails): java.io.Serializable

