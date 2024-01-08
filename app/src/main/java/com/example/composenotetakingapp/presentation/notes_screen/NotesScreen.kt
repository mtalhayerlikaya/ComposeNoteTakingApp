package com.example.composenotetakingapp.presentation.notes_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenotetakingapp.R
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.presentation.StaggeredVerticalGrid

@Composable
fun NotesScreen(
    navController: NavController,
    //viewModel: ViewModel= hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Fab(navController)
    }
}

@Composable
fun Fab(navController: NavController) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("new_note_screen") },
                backgroundColor = Color(255, 193, 7, 255),
                contentColor = Color.White,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    ) {
        AddSearchBarGridLayout(navController)
    }
}

@Composable
fun AddSearchBarGridLayout(navController: NavController) {
    var hideKeyboard by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .verticalScroll(rememberScrollState())
        .clickable {
            hideKeyboard = true
        }
    ) {
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            hideKeyboard = hideKeyboard,
            onFocusClear = { hideKeyboard = false }
        ) {}
        StaggeredGrid(navController)
    }

}

//Static value
/*val staggeredText = listOf(
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s when an unknown printer took a galley of type",
    "Lorem Ipsum is simply dummy text",
    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here'",
    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.",
    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form",
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
)*/
//val staggeredText = emptyList<String>()

@Composable
fun StaggeredGrid(navController: NavController, viewModel: NotesScreenViewModel = hiltViewModel()) {
    val noteList by remember { viewModel.noteFromDB }
    //val noteList = remember { mutableStateListOf(viewModel.noteFromDB) }
    //val noteList by rememberSaveable() { mutableStateOf(viewModel.noteFromDB) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        if (noteList.isNotEmpty()) {
            CustomGridLayout(navController = navController,noteList = noteList)
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Center
            ) {
                Text(
                    text = "You have not noted yet",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Composable
fun CardItem(noteList:List<Note>, navController:NavController) {
    noteList.forEach {note->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable {
                    navController.navigate(
                        "note_detail_screen/${"Deneme"}/${note}"
                    )
                },
            backgroundColor = Color(color = Color.White.toArgb()),
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column {
                Text(
                    text = note.noteHeader ?: "",
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 0.dp),
                    textAlign = TextAlign.Start,

                    )
                Text(
                    text = note.noteContent ?: "",
                    color = Color.DarkGray,
                    modifier = Modifier.padding(
                        paddingValues = PaddingValues(
                            16.dp,
                            16.dp
                        )
                    ),
                    maxLines = 5,
                    //  fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "19 Mayıs",
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp),
                )
            }
        }
    }
}

@Composable
fun CustomGridLayout(navController: NavController,noteList:List<Note>) {
    val mutableStateNoteList by remember { mutableStateOf(noteList)}
    Column(
        modifier = Modifier
            .padding(5.dp, 5.dp, 0.dp, 5.dp)
    ) {
        StaggeredVerticalGrid(
            numColumns = 2, //put the how many column you want
            modifier = Modifier.padding(5.dp)
        ) {
            CardItem(mutableStateNoteList,navController=navController)
        }
           
        /*    mutableStateNoteList.forEach { text ->
                  val rnd = Random()
                val color: Int = android.graphics.Color.argb(255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )


            }*/
        
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
        if (hideKeyboard) {
            focusManager.clearFocus()
            // Call onFocusClear to reset hideKeyboard state to false
            onFocusClear()
        }
    }


}
