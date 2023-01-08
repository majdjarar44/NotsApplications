package com.mcit.notsapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mcit.notsapplication.models.NotesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface DaoDataBase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNotes(userModel: NotesModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNotes(userModel: List<NotesModel>)

    @Query("SELECT * From notes")
    fun getUserNotes(): Flow<List<NotesModel>>


    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(note: NotesModel)

    @Query( "DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id : Int)
}