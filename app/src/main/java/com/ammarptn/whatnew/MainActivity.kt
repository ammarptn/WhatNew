package com.ammarptn.whatnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ammarptn.whatnew.ui.theme.WhatNewTheme
import com.ammarptn.whatsnew.WhatNewActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(applicationContext,WhatNewActivity::class.java)
        intent.putExtra("package_name","com.ammarptn.willow.digital.watch.face")
        intent.putExtra("version","1.0.0")
        startActivity(intent)
    }
}
