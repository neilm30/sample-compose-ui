package com.githubrepo.composeui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextInputComponent(

){
    var text = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = text.value,
        label = { Text("Enter Your Name:" , color = Color.DarkGray, fontWeight = FontWeight.Bold) },
        onValueChange ={ newValue ->
         text.value= newValue
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
        keyboardActions = KeyboardActions(
            onDone = { }
        )
     )
   
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  Demo_DropDownMenu(level: Array<String>,   onClick: (String) -> Unit,) {
    val context = LocalContext.current

    var mSelectedText by remember { mutableStateOf(level[0]) }
    var mExpanded by remember { mutableStateOf(false) }
    onClick.invoke(mSelectedText)

     ExposedDropdownMenuBox(
         expanded = mExpanded ,
         onExpandedChange = {
            mExpanded = !mExpanded
          },
         modifier = Modifier
             .height(50.dp)
             .padding(horizontal = 10.dp)
             .fillMaxWidth()
             .border(2.dp, SolidColor(Color.White), shape = RoundedCornerShape(10.dp))) {
         Text(
             text = mSelectedText,
             modifier = Modifier
                 .fillMaxWidth()
                 .height(50.dp)
                 .wrapContentHeight(align = Alignment.CenterVertically)
                 .menuAnchor(),
             fontSize = 18.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center
         )

         ExposedDropdownMenu(
             expanded = mExpanded,
             onDismissRequest = { mExpanded = false }
         ) {
             level.forEach { item ->
                 DropdownMenuItem(
                     text = {Text(text = item)},
                     onClick = {
                         mSelectedText = item
                         mExpanded = false
                         onClick.invoke(mSelectedText)
                 }
               )
             }
         }
     }
}

@Composable
fun CommonButtonComponent(
    buttontext : String,
    onClick: () -> Unit,
    onEnabled: (Boolean) -> Boolean,
    containerColor: Color = Color.LightGray,
    contentColor: Color = Color.Black
){
    var text = remember { mutableStateOf(TextFieldValue()) }
    val isEnabled = false
val modifierParams = Modifier
    .fillMaxWidth()
    .height(50.dp)
    .padding(horizontal = 16.dp)
    Button(onClick = {
       onClick.invoke()
    }, modifier = modifierParams,
         elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp), shape = RoundedCornerShape(12.dp), border = BorderStroke(width = 2.dp, brush = SolidColor(Color.White)),
        colors = ButtonColors(containerColor = containerColor, contentColor = contentColor, disabledContentColor = Color.LightGray , disabledContainerColor =  Color.Transparent),
        enabled = onEnabled.invoke(isEnabled)
    ) {
        Text(text = buttontext, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SimpleCircularProgressComponent() {
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp).padding(10.dp),
        color = Color.Red,
        strokeWidth = 8.dp,
        trackColor = Color.LightGray,
        strokeCap = StrokeCap.Round
    )
}

@Composable
fun AlertResultDialog(
    openDialog: MutableState<Boolean>,
    title: String,
    confirmBtn: String,
    dismissBtn: String,
    onBobyContent: @Composable () -> Unit,
    onClick: () -> Unit
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = title)
            },
            text = { onBobyContent.invoke() },
                    /* {
                Text(
                    "You scored ${score}/${totalQuestions}",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Blue,
                    fontSize = 30.sp
                )
            },*/
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        onClick.invoke()
                    }) {
                    Text(confirmBtn)
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(dismissBtn)
                }
            }
        )
    }

}

