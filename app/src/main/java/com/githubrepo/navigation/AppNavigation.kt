package com.githubrepo.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.githubrepo.composeui.LoginScreen
import com.githubrepo.composeui.QuestionScreen
import com.githubrepo.composeui.QuizzScoreScreen
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.presentation.login.TrendingQuestionsViewModel
import kotlin.reflect.typeOf

/*@Composable
fun AppNavigation(startDesitination : String = AppDestinations.HOME_SCREEN.route, viewModel: TrendingRepositoryViewModel){
    val navController = rememberNavController()
    val destinations = AppDestinations.HOME_SCREEN

    NavHost(
        navController = navController,
        startDestination = startDesitination
    ) {
       // setHomeScreenDestination(destinations, navController, viewModel)

    }
}*/

@Composable
fun AppNavigationToLoginScreen(viewModel: TrendingQuestionsViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN_SCREEN
    ) {
        setLoginScreenDestination( navController, viewModel)

    }
}

/*private fun NavGraphBuilder.setHomeScreenDestination(
    destinations: AppDestinations,
    navController: NavHostController,
    viewModel: TrendingRepositoryViewModel
) {
    composable(destinations.HOME_SCREEN.route) {

        HomeScreen(navController, viewModel = viewModel)
    }
}*/

private fun NavGraphBuilder.setLoginScreenDestination(
    navController: NavHostController,
    viewModel: TrendingQuestionsViewModel
) {
    var targetRoute = ""
    if (navController.currentDestination?.route !== targetRoute) {
        composable<LOGIN_SCREEN> {
            viewModel.clearStateList()
            LoginScreen(
                navController,
                state = viewModel.dataState.collectAsState().value,
                onEvent = viewModel::onEventChange,
                 viewModel.createCategoryMap()
            )
        }
        composable<QUESTION_SCREEN>(
            typeMap = mapOf(
                typeOf<RepositoriesEntity.QuizzDetails>() to CustomNavType.QuizType
            )
        ) { backStackEntry ->
            val arguments = backStackEntry.toRoute<QUESTION_SCREEN>()
            QuestionScreen(
                navController,
                questionsList = arguments.result,
                state = viewModel.dataState.collectAsState().value,
                onEvent = viewModel::onEventChange,
                resultState =viewModel.resultState.collectAsState().value
            )
        }
        composable<SCORE_SCREEN> {
            QuizzScoreScreen(
                navController,
               score = 5,
               totalQuestions = 10
            )
        }
    }

}


/*private fun NavGraphBuilder.setDetailScreenDestination(
    destinations: AppDestinations,
    navController: NavHostController,
) {
    composable(destinations.HOME_SCREEN) {
        QuestionScreen("20", "5")

    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Text(text = "Hello World")
}

