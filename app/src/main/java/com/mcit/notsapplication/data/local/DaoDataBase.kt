package com.mcit.notsapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mcit.notsapplication.models.NotesModel

@Dao
interface DaoDataBase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNotes(userModel: NotesModel)

    @Query("SELECT * From notes")
    fun getUserNotes():LiveData<List<NotesModel>>


    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(note: NotesModel)

    @Query( "DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id : Int?)
}