package com.mcit.notsapplication.ui.fragment.main

import androidx.lifecycle.ViewModel
import com.mcit.notsapplication.data.local.repo.MainNotesRepo
import com.mcit.notsapplication.data.local.repo.WriteNoteRepo
import com.mcit.notsapplication.models.NotesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepo: MainNotesRepo,val writeRepo: WriteNoteRepo) : ViewModel() {
    val getsUserNotes = mainRepo.getUserNotes()

    lateinit var notes: NotesModel

    fun addUserNotes(item: NotesModel) {
        writeRepo.insertNotes(item)
    }
    fun update(item: NotesModel) {
        writeRepo.updateNotes(item)
    }
    fun deleteNote(id:Int){
        writeRepo.deleteNote(id)
    }
}