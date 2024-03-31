package com.ammarptn.whatnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ammarptn.whatsnew.WhatNewActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this,WhatNewActivity::class.java)
        intent.putExtra("package_name","com.ammarptn.willow.digital.watch.face")
        intent.putExtra("version","1_0_0")
        startActivity(intent)
    }
}
