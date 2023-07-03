package com.example.fitnesstracker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.model.Calculate

class ListCalculateActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calculate)

        val type =
            intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        Thread {
            val app = application as App
            val calculateDao = app.db.calculateDao()
            val response = calculateDao.getRegisterByType(type)

            runOnUiThread {
                Log.i("responseTypeCalculate", "resposta: $response")
            }
        }.start()

        val mainItemsList = mutableListOf<Calculate>()

        recyclerView = findViewById(R.id.recyclerViewCalculate)
        recyclerView.adapter = MyAdapter(mainItemsList)
        recyclerView.layoutManager = LinearLayoutManager(this@ListCalculateActivity)
    }

    private inner class MyAdapter(
        private val mainList: List<Calculate>
    ) : RecyclerView.Adapter<MyAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            return 3
        }

        private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind() {}
        }
    }
}
