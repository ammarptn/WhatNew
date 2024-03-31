package com.ammarptn.whatsnew

import android.content.Context
import com.ammarptn.whatsnew.api.model.DataResponse

class DataContractor {
    interface View {
        fun success(dataResponse: DataResponse)
        fun fail(responseMessage: String?)
    }

    interface Presenter {
        fun getData(context: Context, packetName: String, version: String)
    }
}