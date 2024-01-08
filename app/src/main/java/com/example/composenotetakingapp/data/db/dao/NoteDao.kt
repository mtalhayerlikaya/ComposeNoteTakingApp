package com.example.composenotetakingapp.data.db.dao

import androidx.room.*
import com.example.composenotetakingapp.domain.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE noteId IN (:noteId)")
    suspend fun getNoteByIds(noteId: Int): Note

    @Insert
    suspend fun insert(note: Note)

    //@Update(onConflict = OnConflictStrategy.REPLACE)
    @Query("UPDATE notes SET note_content =:noteContent, note_header = :header WHERE noteId = :id")
    fun update(noteContent:String,header:String,id:Long)
/*
    @Query("UPDATE notes SET note_header=:header and note_content=:content WHERE noteId = :id")
    suspend fun update(header:String,content:String,id:Int)*/

    @Delete
    suspend fun delete(note: Note)
}