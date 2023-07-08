package com.example.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesstracker.model.Calculate

class TmbActivity : AppCompatActivity() {
    private lateinit var lifestyle: AutoCompleteTextView
    private lateinit var weightEditInput: EditText
    private lateinit var heightEditInput: EditText
    private lateinit var ageEditInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        weightEditInput = findViewById(R.id.inputEditTMB_weight)
        heightEditInput = findViewById(R.id.inputEditTMB_height)
        ageEditInput = findViewById(R.id.inputEditTMB_age)
        val buttonTmbCalculator: Button = findViewById(R.id.button_tmbCalculator)

        lifestyle = findViewById(R.id.auto_lifestyle)
        val items = resources.getStringArray(R.array.tmb_lifesyle)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        lifestyle.setText(items.first())
        lifestyle.setAdapter(adapter)

        buttonTmbCalculator.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.toastDefeatValue, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = weightEditInput.text.toString().toInt()
            val height = heightEditInput.text.toString().toInt()
            val age = ageEditInput.text.toString().toInt()

            val result = calculateTmb(weight, height, age)
            val response = tmbRequest(result)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.tmb_response, response))
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setNegativeButton(R.string.save) { _, _ ->
                    Thread {
                        val app = application as App
                        val calculateDao = app.db.calculateDao()
                        calculateDao.insert(
                            Calculate(
                                type = "tmb",
                                res = response
                            )
                        )

                        runOnUiThread {
                            openListActivity()
                        }
                    }.start()
                }
                .create()
                .show()

            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            finish()
            openListActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openListActivity() {
        val intent = Intent(this@TmbActivity, ListCalculateActivity::class.java)
        intent.putExtra("type", "tmb")
        startActivity(intent)
    }

    private fun calculateTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    private fun tmbRequest(tmb: Double): Double {
        val items = resources.getStringArray(R.array.tmb_lifesyle)
        return when {
            lifestyle.text.toString() == items[0] -> tmb * 1.2
            lifestyle.text.toString() == items[1] -> tmb * 1.375
            lifestyle.text.toString() == items[2] -> tmb * 1.55
            lifestyle.text.toString() == items[3] -> tmb * 1.725
            lifestyle.text.toString() == items[4] -> tmb * 1.9
            else -> 0.0
        }
    }

    private fun validate(): Boolean {
        return (weightEditInput.text.toString().isNotEmpty()
                && heightEditInput.text.toString().isNotEmpty()
                && ageEditInput.text.toString().isNotEmpty()
                && !weightEditInput.text.toString().startsWith("0")
                && !heightEditInput.text.toString().startsWith("0")
                && !ageEditInput.text.toString().startsWith("0"))
    }
}
