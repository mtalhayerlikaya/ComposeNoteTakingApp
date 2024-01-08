package com.example.composenotetakingapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenotetakingapp.ui.theme.ComposeNoteTakingAppTheme
import com.example.composenotetakingapp.presentation.NewNoteScreen
import com.example.composenotetakingapp.presentation.note_detail.NoteDetailScreen
import com.example.composenotetakingapp.presentation.notes_screen.NotesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                ComposeNoteTakingAppTheme{

                        val navController = rememberNavController()
                        NavHost(navController = navController,
                            startDestination = "notes_screen"
                        ){
                            composable("notes_screen"){
                                NotesScreen(navController = navController)
                            }
                            composable("note_detail_screen/{noteHeader}/{noteContent}", arguments = listOf(
                                navArgument("noteHeader") {
                                    type = NavType.StringType
                                },
                                navArgument("noteContent") {
                                    type = NavType.StringType
                                }
                            )){
                                val noteHeader = remember {
                                    it.arguments?.getString("noteHeader")
                                }
                                val noteContent = remember {
                                    it.arguments?.getString("noteContent")
                                }
                                NoteDetailScreen(
                                    noteHeader ?: "",
                                    noteContent ?: "",
                                    navController = navController)
                            }
                            composable("new_note_screen"){
                                NewNoteScreen(navController = navController)
                            }


                        }
                    }


                }
            }
    }
