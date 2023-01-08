package com.mcit.notsapplication.data.local.repo

import com.mcit.notsapplication.global.performRemoteOperation2
import com.mcit.notsapplication.data.local.DaoDataBase
import com.mcit.notsapplication.models.NotesModel
import javax.inject.Inject

class MainNotesRepo @Inject constructor(val dataBase: DaoDataBase) {

    fun getUserNotes() = dataBase.getUserNotes()

    fun saveNotes(notes: List<NotesModel>) {
        dataBase.insertAllNotes(notes)
    }
}