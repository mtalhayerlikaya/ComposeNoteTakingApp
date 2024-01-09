package com.example.composenotetakingapp.presentation.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenotetakingapp.domain.model.Note
import com.example.composenotetakingapp.domain.repository.NoteDetailScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailScreenViewModel
@Inject
constructor(private val repo: NoteDetailScreenRepository) : ViewModel() {


    fun insertNote(note: Note) = viewModelScope.launch {
        repo.insertNote(note)
        println("deneme deneme deneme")
    }

}