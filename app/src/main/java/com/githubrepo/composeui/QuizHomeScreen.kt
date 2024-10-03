package com.githubrepo.composeui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController
import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.intent.DataIntent
import com.githubrepo.navigation.QUESTION_SCREEN
import com.githubrepo.state.DataState
import com.githubrepo.state.QuestionResultState
import com.iiab.mobilebanking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    state: DataState,
    onEvent: (DataIntent) -> Unit,
    maps: Map<String, String>
) {

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory  = ""
    var selectedLevel  = ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(R.color.light_green)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.quizz_name),
            modifier = Modifier.padding(horizontal = 10.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextInputComponent()

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.total_questions), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(BorderStroke(width = 2.dp, color = Color.White)),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.select_level), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Demo_DropDownMenu(stringArrayResource(id = R.array.level_options)) {
            selectedLevel = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.select_category), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Demo_DropDownMenu(maps.keys.toTypedArray()) {
            selectedCategory = maps.getValue(it)
        }

        Spacer(modifier = Modifier.height(50.dp))

        CommonButtonComponent(stringResource(id = R.string.Start), {
           onEvent(DataIntent.FetchQuizzQuestions(textFieldValue.text, selectedCategory, selectedLevel))
        }, onEnabled = { enabled ->
            textFieldValue.text.isEmpty().not()
        })

        when (state) {
            is DataState.Loading -> {
                if (state.loading) {
                    SimpleCircularProgressComponent()
                }
            }

            is DataState.Error -> {


            }

            is DataState.Inactive -> {


            }

            is DataState.FinalScore -> {


            }

            is DataState.Success -> {
                LaunchedEffect(Unit) {// need this else compose called multiple times ..not surw why
                    navController.navigate(route = QUESTION_SCREEN(state.data))
                }
            }
        }

    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun QuestionScreen(
    navController: NavHostController,
    questionsList: RepositoriesEntity.QuizzDetails,
    onEvent: (DataIntent) -> Unit,
    state: DataState,
    resultState: List<QuestionResultState>
) {

    val openDialog = remember { mutableStateOf(false) }
    val selectedIndex by remember {
        mutableStateOf(-1)
    }

    var questionIndex by remember {
        mutableStateOf(0)
    }
    val questionIndexDerived: Boolean by derivedStateOf { (questionIndex + 1) == questionsList.getTotalQuestions() }
    val selectedAnswer = remember { mutableStateOf ("") }

  /*  var selectedOption = remember {
        mutableIntStateOf(-1)
    }*/
    val selectedOption = remember {
        mutableIntStateOf(-1)
    }

    var iconAlphaVisibilty by remember {
        mutableFloatStateOf(0F)
    }

    val dstate = remember {
        derivedStateOf {
            -1
        }
    }

    // Log.i("Question","onEventChange selectedLevel index: "+questionIndex)
//if(questionIndex<questionsList.getTotalQuestions()){
    // if(!questionIndexDerived){
    val item = questionsList.results[questionIndex]
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.light_green)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(ButtonDefaults.IconSize)
                    .alpha(iconAlphaVisibilty)
                    .clickable(
                        enabled = true,
                        onClick = {
                            questionIndex-- // decrease when going back
                            Log.i("Question", "onEventChange selectedIndex: " + resultState)
                            selectedOption.intValue = resultState[questionIndex].selectedIndex

                            if (questionIndex == 0)
                                iconAlphaVisibilty = 0F
                        }
                    ),
            )


            Text(
                text = "${questionIndex + 1}/${questionsList.getTotalQuestions()}",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )
            // Text(text = "30s", color = Color.Black, modifier = Modifier.padding(10.dp))

            Icon(
                painter = painterResource(android.R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(ButtonDefaults.IconSize).clickable(
                        onClick = {

                        }
                    ),
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .weight(0.5f)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 10.dp),
                color = Color.White,
                shadowElevation = 40.dp,
                shape = RoundedCornerShape(16.dp),
            ) {
                item.question?.let { ques ->
                    Text(
                        text = ques,
                        modifier = Modifier
                            .wrapContentHeight(),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold
                    )

                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            RadioButtonComponent(item.all_answers, selectedOption) { answer ->
                //selectedAnswer.value = answer
                onEvent(
                    DataIntent.UpdateAnswerList(
                        QuestionResultState(
                            selectedIndex = selectedOption.intValue,
                            selectedAnswer = answer,
                            isSelectedAnswerCorrect = item.isSelectedCorrect(answer)
                        )
                    )
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (questionIndexDerived) {
                        onEvent(DataIntent.GetTotalScore)
                        openDialog.value = true
                    } else {
                       /* onEvent(
                            DataIntent.UpdateAnswerList(
                                QuestionResultState(
                                    selectedIndex = selectedOption.intValue,
                                    selectedAnswer = selectedAnswer.value,
                                    isSelectedAnswerCorrect = if(selectedAnswer.value.isEmpty().not())item.isSelectedCorrect(selectedAnswer.value) else false
                                )
                            )
                        )*/
                        questionIndex++//10
                        try{
                            selectedOption.intValue = resultState[questionIndex].selectedIndex

                        }catch(e:Exception){
                            selectedOption.intValue = -1
                        }


                      /*  if (resultState.size > questionIndex) {
                            selectedOption.intValue = resultState[questionIndex].selectedIndex
                        } else {
                            selectedOption.intValue = -1
                        }*/

                        iconAlphaVisibilty = 1F // to make view visible/invisible
                    }
                },
                enabled =  (selectedOption.intValue != -1),
                modifier = Modifier
                    .fillMaxSize(),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 40.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 2.dp, brush = SolidColor(Color.White)
                ),
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.dark_green),
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Gray
                )

            ) {
                if (item.questionNumber == questionsList.getTotalQuestions()) {
                    Text(text = "Done", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                } else {
                    Text(text = "Next", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
    // }
    when (state) {
        is DataState.FinalScore -> {
            AlertResultDialog(
               openDialog = openDialog,
                title = stringResource(id = R.string.final_score_title),
                confirmBtn = stringResource(id = R.string.play_again),
                dismissBtn = stringResource(id = R.string.review),
                onBobyContent = {
                    Text(
                        "You scored ${state.score}/${questionsList.getTotalQuestions()}",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Blue,
                        fontSize = 30.sp
                    )
                },
            ) {
                navController.popBackStack()
            }
        }
        else -> {}
    }


}



@Composable
fun QuizzScoreScreen(navController: NavHostController, score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Text(text = "Your Final Score ----", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Correct ${score / totalQuestions}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


    }
}

@Composable
fun RadioButtonComponent(
    allAnswers: List<String>,
    selectedOption: MutableIntState,
    onClick: (String) -> Unit
) {

    allAnswers.forEachIndexed { index, option ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        selectedOption.intValue = index
                        onClick.invoke(option)
                    }
                ),
            color = if (selectedOption.intValue == index) colorResource(id = R.color.very_light_green) else Color.White,
            shadowElevation = 10.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
                RadioButton(
                    selected = selectedOption.intValue == index,
                    onClick = {
                        selectedOption.intValue = index
                        onClick.invoke(option)
                    }
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun ComposeConstraintLayout() {
    val constraints = ConstraintSet {
        val icon = createRefFor("icon")
        val question = createRefFor("question")
        val timer = createRefFor("timer")
        constrain(icon) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }

    }
}