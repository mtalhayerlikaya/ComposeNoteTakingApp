package com.example.composenotetakingapp.presentation.notes_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenotetakingapp.data.repository.NotesScreenRepositoryImpl
import com.example.composenotetakingapp.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel
@Inject
constructor(private val repo: NotesScreenRepositoryImpl) : ViewModel() {
    private var notesFromDB = mutableStateOf<List<Note>>(mutableListOf())
    val noteFromDB
        get() = notesFromDB

    init {
        getAllNotesFromDB()
    }

    fun getAllNotesFromDB() = viewModelScope.launch {
        notesFromDB.value = repo.getNotesFromDB()
    }

    suspend fun getNoteFromDbById(noteId: Int): Note {
        return repo.getNoteFromDbById(noteId = noteId)
    }

    suspend fun insertNoteToDB(note: Note) {
        repo.insertNote(note)
    }

    suspend fun deleteNoteFromDB(note: Note) {
        repo.deleteNote(note)
    }


}