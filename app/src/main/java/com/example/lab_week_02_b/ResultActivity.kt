package com.example.lab_week_02_b

import android.graphics.Color // Import for Color
import android.os.Bundle
import android.widget.TextView // Import for TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout // Import for ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)
            val backgroundScreen =
                findViewById<ConstraintLayout>(R.id.background_screen)
            // It's a good practice to check if colorCode is not null before parsing
            colorCode?.let {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$it"))
            }
            val resultMessage =
                findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,
                colorCode?.uppercase())
        }
    }
}
