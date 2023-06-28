package com.example.fitnesstracker

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemOnClickInterface {
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
                id = 2,
                drawableId = R.drawable.baseline_whatshot_24,
                textStringId = R.string.button2,
                color = Color.GRAY
            )
        )

        val myAdapter = MyAdapter(mainItemsList, this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onClick() {
        TODO("Not yet implemented")
    }

    private inner class MyAdapter(
        private val itemsList: List<MainItem>,
        private val itemOnClickInterface: ItemOnClickInterface
    ) :
        RecyclerView.Adapter<MyAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val item = itemsList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return itemsList.size
        }

        private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: MainItem) {
                val imageItem: ImageView = itemView.findViewById(R.id.item_image)
                val nameItem: TextView = itemView.findViewById(R.id.item_text_name)
                val containerItem: LinearLayout = itemView.findViewById(R.id.item_container)

                imageItem.setImageResource(item.drawableId)
                nameItem.setText(item.textStringId)
                containerItem.setBackgroundColor(item.color)

                containerItem.setOnClickListener {
                    itemOnClickInterface.onClick()
                }
            }
        }
    }
}
