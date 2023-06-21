package com.example.fitnesstracker

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
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
            val weight = weightEditInput.text.toString().toInt()
            val height = heightEditInput.text.toString().toInt()

            val result = calculateImc(weight, height)

            val imcStringResult = imcResponse(result)

            val dialog = AlertDialog.Builder(this)

            dialog.setTitle(getString(R.string.imc_response, result))
            dialog.setMessage(imcStringResult)
            dialog.setPositiveButton("Click", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            val dialogCreate = dialog.create()
            dialogCreate.show()
        }
    }

    @StringRes
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
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
