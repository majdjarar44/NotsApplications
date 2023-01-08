package com.mcit.notsapplication.data.remote

import com.mcit.notsapplication.models.NotesModel
import retrofit2.http.GET


interface AppService {
 @GET("mmmm")
 fun getNotes():List<NotesModel>

}