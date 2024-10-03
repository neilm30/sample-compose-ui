package com.githubrepo.composeui

import android.R
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.intent.DataIntent
import com.githubrepo.presentation.login.TrendingRepositoryViewModel
import com.githubrepo.state.DataState


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController, viewModel: TrendingRepositoryViewModel){

    val scope = rememberCoroutineScope()
   // val recipeListState = viewModel.dataState.collectAsState()
    val state by viewModel.dataState

            when(state){
                is DataState.Loading -> {
                    Log.d("Worked", "Idle")
                }

                is DataState.Success -> {
                    val result = (state as DataState.Success).data
                //   GithubRepoList(result)

                }
                is DataState.Error -> {

                }
                is DataState.Inactive -> {

                }

                is DataState.FinalScore -> {


                }
            }
    LaunchedEffect(Unit) {
        viewModel.userIntent.send(DataIntent.FetchReoistory)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepoList(data: List<RepositoriesEntity.RepositoryDetails>) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GitHubList") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.White
                )
            )
        },
    ) { paddingValues ->
        LazyColumn(contentPadding = PaddingValues(5.dp), modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)){
            repeat(3){
                items(data){ item ->
                    ListRow(item, onItemClick = {

                    })
                }
            }

        }
    }
}

@Composable
fun ListRow(item: RepositoriesEntity.RepositoryDetails,  onItemClick: (String) -> Unit) {

    Row(modifier = Modifier
        .padding(vertical = 8.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            onItemClick(item.description ?: "")
        }
    ) {
        /*.clip(RoundedCornerShape(5.dp))
        .fillMaxWidth()
        .padding(5.dp, 5.dp)
        .wrapContentHeight()
        .padding(10.dp, 5.dp)
        .border(3.dp,Color.Gray)
        .clickable {
            onItemClick(item.description?:"")
        }*/

        AsyncImage(
            modifier = Modifier
                .size(70.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.avatar)
                .crossfade(true)
                .transformations(CircleCropTransformation())
                .build(),
            alignment = Alignment.Center,
            contentDescription = "default crossfade example"

        )
        Spacer(modifier = Modifier.width(6.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
           , verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = item.author?:"", modifier = Modifier.fillMaxWidth(), color = Color.DarkGray, fontSize = 24.sp, letterSpacing = 2.sp)

                Text(text = item.name?:"" , modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp), color = Color.Red, fontSize = 16.sp )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                painterResource(R.drawable.ic_delete),
                contentDescription = ""
            )

        }
    }
    HorizontalDivider(modifier = Modifier.padding(horizontal = 3.dp), color = Color.LightGray)

  /*  Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(Color.Gray)){
        Image(painter = painterResource(id = model.image ),
            contentDescription = "Fruit image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp))
        Text(text = model.name, color = Color.Red, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    }*/

}