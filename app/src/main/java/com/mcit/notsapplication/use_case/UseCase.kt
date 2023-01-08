package com.mcit.notsapplication.use_case

import android.util.Log
import com.mcit.notsapplication.data.local.repo.MainNotesRepo
import com.mcit.notsapplication.data.local.repo.WriteNoteRepo
import com.mcit.notsapplication.data.remote.RemoteDataSource
import com.mcit.notsapplication.exceptions.GenericException
import com.mcit.notsapplication.exceptions.NetworkException
import com.mcit.notsapplication.models.NotesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val httpRepository: RemoteDataSource,
    private val localRepository: MainNotesRepo,
    private val localWriteNotes:WriteNoteRepo
) {
    private val tag = "useCase"

    /**
     * load all currencies from API and then store them inside local database for future uses
     */
    @Throws(NetworkException::class, GenericException::class)
    fun getAllNotes(): Flow<List<NotesModel>> {
        try {
            //loading for local repository
            var listLocalNotes = localRepository.getUserNotes()
            //if empty, fetching from API
            if (false) {//currencies.isEmpty()
                val currenciesMap = httpRepository.getAllNotes()
                localRepository.saveNotes(currenciesMap)
            }
            return listLocalNotes
        } catch (networkException: NetworkException) {
            Log.d(tag, "a network exception occurred, rethrowing it")
            throw networkException
        } catch (exception: Exception) {
            Log.w(tag, "an exception occurred: ${exception.message}", exception)
            throw GenericException()
        }
    }


    fun addUserNotes(item: NotesModel) {
        try {
            localWriteNotes.insertNotes(item)
        } catch (networkException: NetworkException) {
            Log.d(tag, "a network exception occurred, rethrowing it")
            throw networkException
        } catch (exception: Exception) {
            Log.w(tag, "an exception occurred: ${exception.message}", exception)
            throw GenericException()
        }
    }

    fun updateNote(item: NotesModel) {
        try {
            localWriteNotes.updateNotes(item)
        } catch (networkException: NetworkException) {
            Log.d(tag, "a network exception occurred, rethrowing it")
            throw networkException
        } catch (exception: Exception) {
            Log.w(tag, "an exception occurred: ${exception.message}", exception)
            throw GenericException()
        }
    }

    fun deleteNote(id:Int){
        try {
            localWriteNotes.deleteNote(id)
        } catch (networkException: NetworkException) {
            Log.d(tag, "a network exception occurred, rethrowing it")
            throw networkException
        } catch (exception: Exception) {
            Log.w(tag, "an exception occurred: ${exception.message}", exception)
            throw GenericException()
        }
    }
}