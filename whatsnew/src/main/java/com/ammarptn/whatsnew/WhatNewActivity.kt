package com.ammarptn.whatsnew

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.ammarptn.whatsnew.api.model.DataResponse

class WhatNewActivity : AppCompatActivity(),DataContractor.View {
    lateinit var presenter: DataPresenter
    lateinit var packetName: String
    lateinit var version: String
    lateinit var customAdapter: CustomAdapter
    lateinit var viewPager: ViewPager
    lateinit var titleView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_new)
        val bundle: Bundle? = intent.extras
        packetName = bundle!!.getString("package_name").toString()
        version = bundle!!.getString("version").toString()
        titleView = findViewById(R.id.title)
        viewPager = findViewById(R.id.viewPager)
        presenter = DataPresenter(this)
        presenter.getData(this,packetName,version)
    }

    override fun success(dataResponse: DataResponse) {
        titleView.text = dataResponse.title
        viewPager.adapter = CustomAdapter(dataResponse.data, this@WhatNewActivity)
    }

    override fun fail(responseMessage: String?) {
        TODO("Not yet implemented")
    }
}