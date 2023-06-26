package com.example.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var buttonImc: LinearLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonImc = findViewById(R.id.button_imc)
        buttonImc.setOnClickListener() {
            val intent = Intent(this, ImcActivity::class.java)
            startActivity(intent)
        }

        val myAdapter = MyAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private inner class MyAdapter : RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val item = "Item ${position + 1}"
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return 5
        }
    }

    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemTextView: TextView = view.findViewById(R.id.textView_item)

        fun bind(item: String) {
            itemTextView.text = item
        }
    }
}
