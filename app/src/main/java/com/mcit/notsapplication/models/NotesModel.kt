package com.mcit.notsapplication.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Entity(tableName = "notes")
@Parcelize
data class NotesModel @Inject constructor(@PrimaryKey(autoGenerate = true) val id:Int, val title: String, val notes: String):Parcelable