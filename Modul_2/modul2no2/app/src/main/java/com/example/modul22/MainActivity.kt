package com.example.modul22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rcadapter: RecyclerView
    private val list = ArrayList<galaxy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcadapter = findViewById(R.id.viewlist)
        rcadapter.setHasFixedSize(true)

        list.addAll(galaxy_data.listdata)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        TODO("Not yet implemented")
        rcadapter.layoutManager = LinearLayoutManager(this)
        val listpublisher = adapter(list)
        rcadapter.adapter = listpublisher
    }
}