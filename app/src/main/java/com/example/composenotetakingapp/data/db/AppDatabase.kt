package com.example.composenotetakingapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.data.db.dao.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}