package com.example.composenotetakingapp.domain.repository

import com.example.composenotetakingapp.domain.model.Note

interface NotesScreenRepository {
    suspend fun getNotesFromDB():List<Note>
    suspend fun getNoteFromDbById(noteId:Int): Note
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
}