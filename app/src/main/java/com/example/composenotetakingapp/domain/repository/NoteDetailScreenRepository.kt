package com.example.composenotetakingapp.domain.repository

import com.example.composenotetakingapp.domain.model.Note

interface NoteDetailScreenRepository {

   suspend fun saveNoteToDB(note: Note)

   suspend fun insertNote(note: Note)

}