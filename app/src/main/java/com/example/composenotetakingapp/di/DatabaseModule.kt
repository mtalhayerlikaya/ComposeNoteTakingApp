package com.example.composenotetakingapp.di

import android.content.Context
import androidx.room.Room
import com.example.composenotetakingapp.data.db.AppDatabase
import com.example.composenotetakingapp.data.db.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx:Context): AppDatabase {
        return Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "notes"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

}