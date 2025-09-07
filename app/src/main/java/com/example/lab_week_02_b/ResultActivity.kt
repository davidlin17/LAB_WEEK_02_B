package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button     // Import Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val buttonBack = findViewById<Button>(R.id.button_back)
        buttonBack.setOnClickListener {
            finish()
        }

        // Kode original Anda
        if (intent != null) {
            val colorCode = intent.getStringExtra(COLOR_KEY)
            val backgroundScreen =
                findViewById<ConstraintLayout>(R.id.background_screen)
            try {
                // Pastikan colorCode tidak null sebelum mencoba parse dan set warna
                if (colorCode != null) {
                    backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
                } else {
                    Intent().let { errorIntent ->
                        errorIntent.putExtra(ERROR_KEY, true)
                        setResult(Activity.RESULT_OK, errorIntent) // Atau RESULT_CANCELED
                        finish()
                        return // Penting
                    }
                }
            } catch (ex: IllegalArgumentException) {
                Intent().let { errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                    return // Penting
                }
            }

            val resultMessage =
                findViewById<TextView>(R.id.color_code_result_message)
            // Tampilkan pesan hanya jika colorCode valid dan tidak null
            if (colorCode != null) {
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )
            } else {
                // Jika colorCode null di sini (misalnya karena intent tidak valid dan tidak di-return di atas)
                resultMessage.text = "Invalid or missing color code"
            }
        }
    }
}
