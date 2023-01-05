package com.mcit.notsapplication.data.remote

import com.mcit.notsapplication.global.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val appService: AppService) : BaseDataSource(){

}