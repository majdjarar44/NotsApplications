package com.mcit.notsapplication.ui.fragment.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcit.notsapplication.data.local.repo.MainNotesRepo
import com.mcit.notsapplication.data.local.repo.WriteNoteRepo
import com.mcit.notsapplication.models.NotesModel
import com.mcit.notsapplication.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepo: MainNotesRepo,val writeRepo: WriteNoteRepo,val useCase: UseCase) : ViewModel() {

    fun getsUserNotes(): Flow<List<NotesModel>> = useCase.getAllNotes()
    lateinit var notes: NotesModel

    fun addUserNotes(item: NotesModel) {
        useCase.addUserNotes(item)
    }
    fun update(item: NotesModel) {
        useCase.updateNote(item)
    }
    fun deleteNote(id:Int){
        useCase.deleteNote(id)
    }
}