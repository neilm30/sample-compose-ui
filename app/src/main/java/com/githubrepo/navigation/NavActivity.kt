package com.githubrepo.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.githubrepo.composeui.LoginScreen
import com.githubrepo.presentation.login.TrendingQuestionsViewModel
import com.githubrepo.presentation.login.TrendingRepositoryViewModel
import com.githubrepo.theme.JetPackComposeBasicTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavActivity : ComponentActivity() {

    val trendingRepositoryViewModel : TrendingRepositoryViewModel by viewModels()
    val viewModel : TrendingQuestionsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*LoginScreen(
                state = viewModel.dataState.collectAsState().value,
                onEvent = viewModel::onEventChange

            )*/
       JetPackComposeBasicTheme {
           AppNavigationToLoginScreen(viewModel = viewModel)
         }
        }
    }

}