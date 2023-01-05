package com.mcit.notsapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcit.notsapplication.models.NotesModel

@Database(entities = [NotesModel::class], version = 2)
abstract class NotesDataBase : RoomDatabase() {
        abstract fun userNotes(): DaoDataBase
        companion object {
            @Volatile private var instance: NotesDataBase? = null
            fun getDatabase(context: Context): NotesDataBase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }
            private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, NotesDataBase::class.java, "data_base")
                    .fallbackToDestructiveMigration()
                    .build()
        }

}