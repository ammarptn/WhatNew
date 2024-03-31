package com.ammarptn.whatsnew.api

import com.ammarptn.whatsnew.api.model.DataResponse
import retrofit2.Call
import retrofit2.http.*
interface ApiServices {
    @GET("changelog/{package_name}/{version}.json")
    fun getData(@Path("package_name") packageName:String,@Path("version") version:String,): Call<DataResponse>
}