package com.mcit.notsapplication.data.local.repo

import com.mcit.notsapplication.global.performRemoteOperation2
import com.mcit.notsapplication.data.local.DaoDataBase
import javax.inject.Inject

class MainNotesRepo @Inject constructor(val dataBase: DaoDataBase){

    fun getUserNotes() = performRemoteOperation2 {
        dataBase.getUserNotes()
    }

}