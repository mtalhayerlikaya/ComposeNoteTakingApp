package com.example.composenotetakingapp.di

import com.example.composenotetakingapp.data.db.dao.NoteDao
import com.example.composenotetakingapp.data.repository.NoteDetailScreenRepositoryImpl
import com.example.composenotetakingapp.domain.repository.NoteDetailScreenRepository
import com.example.composenotetakingapp.presentation.note_detail.NoteDetailScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

  /*  @Singleton
    @Provides
    fun provideNoteDetailScreenViewModel(repo: NoteDetailScreenRepository): NoteDetailScreenViewModel {
        return NoteDetailScreenViewModel(repo)
    }*/

    @Singleton
    @Provides
    fun provideNoteNoteDetailScreenRepo(dao:NoteDao): NoteDetailScreenRepository {
        return NoteDetailScreenRepositoryImpl(dao)
    }

}