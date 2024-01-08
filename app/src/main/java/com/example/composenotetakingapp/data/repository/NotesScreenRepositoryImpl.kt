package com.example.composenotetakingapp.data.repository

import com.example.composenotetakingapp.domain.repository.NotesScreenRepository
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.data.db.dao.NoteDao
import javax.inject.Inject

class NotesScreenRepositoryImpl
@Inject
constructor(private val dao: NoteDao): NotesScreenRepository {

    override suspend fun getNotesFromDB():List<Note>{
        return dao.getAllNotes()
    }

    override suspend fun getNoteFromDbById(noteId: Int): Note {
        val note = dao.getNoteByIds(noteId = noteId)
        return note
    }

    override suspend fun insertNote(note: Note) {

    }

    override suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }


}
