package com.example.fitnesstracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ListCalculateActivity : AppCompatActivity() {
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


    }
}
