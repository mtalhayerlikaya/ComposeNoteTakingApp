package com.example.composenotetakingapp.data.repository

import com.example.composenotetakingapp.data.db.dao.NoteDao
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.domain.repository.NoteDetailScreenRepository
import javax.inject.Inject

class NoteDetailScreenRepositoryImpl
@Inject
constructor(private val dao: NoteDao) : NoteDetailScreenRepository {

    override suspend fun saveNoteToDB(note: Note) {
        val notes = dao.getAllNotes()
        notes.forEach {
            if (it.noteId == note.noteId) {
                dao.update(note.noteContent ?: "", note.noteHeader ?: "", note.noteId)
                return
            }
        }
    }

    override suspend fun insertNote(note: Note) {
        dao.insert(note)
    }

}