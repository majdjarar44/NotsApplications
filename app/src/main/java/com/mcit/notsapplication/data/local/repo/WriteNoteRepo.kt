package com.mcit.notsapplication.data.local.repo

import com.mcit.notsapplication.global.main
import com.mcit.notsapplication.data.local.DaoDataBase
import com.mcit.notsapplication.models.NotesModel
import javax.inject.Inject

class WriteNoteRepo @Inject constructor(val dataBase: DaoDataBase){
    fun insertNotes(item: NotesModel) = main {
        dataBase.insertNotes(item)
    }
    fun updateNotes(note:NotesModel)= main{
        dataBase.updateNote(note)
    }
    fun deleteNote(id:Int)= main{
        dataBase.deleteNote(id)
    }
}