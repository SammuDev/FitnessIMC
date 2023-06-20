package com.example.fitnesstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {
    private lateinit var weightEditInput: EditText
    private lateinit var heightEditInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        weightEditInput = findViewById(R.id.inputEdit_weight)
        heightEditInput = findViewById(R.id.inputEdit_height)

        val buttonImcCalculator: Button = findViewById(R.id.button_sendImcCalculator)
        buttonImcCalculator.setOnClickListener {
            validate()
        }
    }
    private fun validate(): Boolean {
        if(weightEditInput.text.toString().isNotEmpty()
            && heightEditInput.text.toString().isNotEmpty()
            && !weightEditInput.text.toString().startsWith("0")
            && !heightEditInput.text.toString().startsWith("0")) {
            return true
        } else {
            return false
        }
    }
}
