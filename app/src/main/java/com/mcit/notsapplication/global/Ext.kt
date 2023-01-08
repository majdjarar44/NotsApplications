package com.mcit.notsapplication.global


import android.content.SharedPreferences

import android.util.Patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Result.Companion.success


inline fun <reified T : Any> SharedPreferences.getArrayList(key: String): ArrayList<T> {
    var ret = this.getString(key, null).toString()
    if (ret.isNullOrBlank())
        return arrayListOf()
    val type = TypeToken.getParameterized(List::class.java, T::class.java).type

    return Gson().fromJson(ret, type)
}

fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch { work() }
