package com.example.fitnesstracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.model.Calculate
import java.text.SimpleDateFormat
import java.util.*

class ListCalculateActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calculate)

        val type =
            intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        val mainItemsList = mutableListOf<Calculate>()

        recyclerView = findViewById(R.id.recyclerViewCalculate)
        recyclerView.adapter = MyAdapter(mainItemsList)
        recyclerView.layoutManager = LinearLayoutManager(this@ListCalculateActivity)

        Thread {
            val app = application as App
            val calculateDao = app.db.calculateDao()
            val response = calculateDao.getRegisterByType(type)

            runOnUiThread {
                mainItemsList.addAll(response)
            }
        }.start()
    }

    private inner class MyAdapter(
        private val mainList: List<Calculate>
    ) : RecyclerView.Adapter<MyAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val item = mainList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return mainList.size
        }

        private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: Calculate) {
                val itemListCalculate: TextView = itemView.findViewById(R.id.itemList_calculate)

                val simpleDateformat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val data = simpleDateformat.format(item.createdDate)
                val result = item.res

                itemListCalculate.text = getString(R.string.listCalculate_response, result, data)
            }
        }
    }
}
