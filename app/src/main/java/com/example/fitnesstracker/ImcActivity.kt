package com.example.fitnesstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
            if (!validate()) {
                Toast.makeText(this, R.string.toastDefeatValue, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = weightEditInput.toString().toInt()
            val height = heightEditInput.toString().toInt()
            val resultOfCalculateimc = calculateImc(weight, height)

            val imcId = responseForImcCalculate(resultOfCalculateimc)
            Toast.makeText(this, imcId, Toast.LENGTH_SHORT).show()
        }
    }
    private fun responseForImcCalculate(imc: Double): Int {
        return if (imc <= 15.0) R.string.imc_severely_low_weight
        else R.string.normal
    }

    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        return (weightEditInput.text.toString().isNotEmpty()
                && heightEditInput.text.toString().isNotEmpty()
                && !weightEditInput.text.toString().startsWith("0")
                && !heightEditInput.text.toString().startsWith("0"))
    }
}
