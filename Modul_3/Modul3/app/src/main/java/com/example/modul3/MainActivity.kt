package com.example.modul3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.btn_int1)
        val btn2 = findViewById<Button>(R.id.btn_int2)
        btn2.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://qrcodescan.in/"))
            startActivity(intent)
        })
        btn1.setOnClickListener{Intent(this,penjelasan::class.java).also { startActivity(it) }}
        }



}