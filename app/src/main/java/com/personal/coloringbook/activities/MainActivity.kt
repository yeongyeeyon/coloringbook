package com.personal.coloringbook.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.personal.coloringbook.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToColoringPageSelection()
    }

    private fun navigateToColoringPageSelection() {
        val intent = Intent(this, ColoringPageSelectionActivity::class.java)
        startActivity(intent)
        finish()
    }
}