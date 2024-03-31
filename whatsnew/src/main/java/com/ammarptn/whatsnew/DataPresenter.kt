package com.ammarptn.whatsnew

import android.content.Context
import com.ammarptn.whatsnew.api.ApiServices
import com.ammarptn.whatsnew.api.ServiceBuilder
import com.ammarptn.whatsnew.api.model.DataResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataPresenter(private var view: DataContractor.View) : DataContractor.Presenter {

    override fun getData(context: Context, packet:String, version:String) {
        ServiceBuilder.buildService(context, ApiServices::class.java).getData(packet,version)
            .enqueue(object : Callback<DataResponse> {

                override fun onResponse(
                    call: Call<DataResponse>?,
                    response: Response<DataResponse>?
                ) {
                    if (response!!.isSuccessful) {
                        val result = response.body()
                        if (result != null) {
                            view.success(result)
                        }
                    } else {
                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            view.fail(jObjError.getString("message"))
                        } catch (e: Exception) {
                            view.fail("Please try again.")
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse>?, t: Throwable?) {
                    view.fail("Please try again.")
                }
            })
    }


}