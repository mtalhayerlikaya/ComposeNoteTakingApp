package com.example.composenotetakingapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true) val noteId: Long,
    @ColumnInfo(name = "note_header") val noteHeader: String?,
    @ColumnInfo(name = "note_content") val noteContent: String?
)