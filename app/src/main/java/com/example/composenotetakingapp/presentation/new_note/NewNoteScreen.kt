package com.example.composenotetakingapp.presentation


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenotetakingapp.R
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.presentation.note_detail.NoteDetailScreenViewModel
import java.util.*


@Composable
fun NewNoteScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        HandleNewNoteScreen(navController = navController)
    }
}

@Composable
fun HandleMainColumn(navController: NavController, icon: (@Composable () -> Unit)?) {
    var header by remember { mutableStateOf(TextFieldValue("")) }
    var content by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        /*   Card(
               modifier = Modifier
                   .widthIn(min = 24.dp)
                   .heightIn(min = 24.dp)
                   .padding(20.dp,20.dp,0.dp,0.dp),

           ) {*/
        icon
        BasicTextField(
            value = header,
            onValueChange = { header = it },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .background(Color.White)
                        //.padding(16.dp)
                        .fillMaxWidth()
                ) {

                    if (header.text.isEmpty()) {
                        Text("Header", color = Color.LightGray)
                    }
                    // <-- Add this
                    innerTextField()
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp, 15.dp, 0.dp, 15.dp)

        )
        BasicTextField(
            value = content,
            onValueChange = { content = it },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .background(Color.White)
                        //.padding(16.dp)
                        .fillMaxWidth()
                ) {

                    if (content.text.isEmpty()) {
                        Text("Start typing...", color = Color.LightGray)
                    }
                    // <-- Add this
                    innerTextField()
                }
            },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

    }

}


@Composable
fun HandleNewNoteScreen(navController: NavController,viewModel: NoteDetailScreenViewModel = hiltViewModel()) {
//    var header by remember { mutableStateOf(TextFieldValue("")) }
//    var content by remember { mutableStateOf(TextFieldValue("")) }
    var header by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var content by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painterResource(R.drawable.ic_back_button),
                contentDescription = "",
                modifier = Modifier
                    .padding(18.dp, 20.dp, 0.dp, 0.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                alignment = Alignment.TopStart
            )

            if (header.text != "" || content.text != "") {

                Image(
                    painterResource(R.drawable.ic_check),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(0.dp, 20.dp, 18.dp, 0.dp)
                        .clickable {
                        viewModel.insertNote(Note(UUID.randomUUID().mostSignificantBits,header.text,content.text))
                        },
                    alignment = Alignment.TopEnd
                )
            }
        }


        Column(modifier = Modifier.fillMaxSize()) {
            BasicTextField(
                value = header,
                onValueChange = { header = it },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .background(Color.White)
                            //.padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        if (header.text.isEmpty()) {
                            Text("Header", color = Color.LightGray)
                        }
                        // <-- Add this
                        innerTextField()
                    }
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp, 15.dp, 0.dp, 15.dp)

            )
            BasicTextField(
                value = content,
                onValueChange = { content = it },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .background(Color.White)
                            //.padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        if (content.text.isEmpty()) {
                            Text("Start typing...", color = Color.LightGray)
                        }
                        // <-- Add this
                        innerTextField()
                    }
                },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)
            )
        }

    }


}