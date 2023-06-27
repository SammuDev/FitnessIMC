package com.example.fitnesstracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var buttonImc: LinearLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItemsList = mutableListOf<MainItem>()
        mainItemsList.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.baseline_wb_sunny_24,
                textStringId = R.string.button1,
                color = Color.CYAN
            )
        )
        mainItemsList.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.baseline_wb_sunny_24,
                textStringId = R.string.button2,
                color = Color.CYAN
            )
        )

        val myAdapter = MyAdapter(mainItemsList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonImc = findViewById(R.id.button_imc)
        buttonImc.setOnClickListener() {
            val intent = Intent(this, ImcActivity::class.java)
            startActivity(intent)
        }

    }

    private inner class MyAdapter(private val itemsList: List<MainItem>) :
        RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//            val item = "Item ${position + 1}"
            val item = itemsList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return itemsList.size
        }
    }

    private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MainItem) {
            val buttonItem: Button = itemView.findViewById(R.id.button_item)
            buttonItem.setText(item.textStringId)
        }
    }
}
