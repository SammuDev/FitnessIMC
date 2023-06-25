package com.example.fitnesstracker

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var btnImc: LinearLayout

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainAdapter = MainAdapter()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

//        btnImc = findViewById(R.id.btn_imc)
//        btnImc.setOnClickListener() {
//            val intent = Intent(this, ImcActivity::class.java)
//            startActivity(intent)
//        }
    }

    private inner class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            TODO("Not yet implemented")
        }
    }

    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
