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

        val calculateItemsList = mutableListOf<Calculate>()
        val myAdapter = MyAdapterList(calculateItemsList)

        recyclerView = findViewById(R.id.recyclerViewCalculate)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        Thread {
            val app = application as App
            val calculateDao = app.db.calculateDao()
            val response = calculateDao.getRegisterByType(type)

            runOnUiThread {
                calculateItemsList.addAll(response)
                myAdapter.notifyDataSetChanged()
            }
        }.start()
    }

    private inner class MyAdapterList(
        private val listCalculate: List<Calculate>
    ) : RecyclerView.Adapter<MyAdapterList.ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.calculate_item, parent, false)
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val item = listCalculate[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return listCalculate.size
        }

        private inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: Calculate) {
                val itemListCalculate: TextView = itemView.findViewById(R.id.itemList_calculate)

                val result = item.res
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val data = simpleDateFormat.format(item.createdDate)

                itemListCalculate.text = getString(R.string.listCalculate_response, result, data)
            }
        }
    }
}
