package com.example.composenotetakingapp.presentation.note_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composenotetakingapp.R

@Composable
fun NoteDetailScreen(noteHeader: String, noteContent: String, navController: NavController) {
    Surface(
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        HandleDetailScreen(navController = navController, noteHeader = noteHeader, noteContent = noteContent)

    }
}

@Composable
fun HandleDetailScreen(navController: NavController,noteHeader: String,noteContent: String) {
    var header by remember { mutableStateOf(TextFieldValue(noteHeader)) }
    var content by remember { mutableStateOf(TextFieldValue(noteContent)) }

    Column(modifier = Modifier.fillMaxSize()) {
        /*   Card(
               modifier = Modifier
                   .widthIn(min = 24.dp)
                   .heightIn(min = 24.dp)
                   .padding(20.dp,20.dp,0.dp,0.dp),

           ) {*/
        Image(
            painterResource(R.drawable.ic_back_button),
            contentDescription = "",
            modifier = Modifier
                .padding(18.dp, 20.dp, 0.dp, 0.dp)
                .clickable {
                    navController.popBackStack()
                }

        )


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
fun DetailNote() {

}

