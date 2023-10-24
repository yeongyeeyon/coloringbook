package com.personal.coloringbook.activities

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.personal.coloringbook.R
import com.personal.coloringbook.adapters.ColoringPageAdapter
import com.personal.coloringbook.fragments.ColoringActivity
import com.personal.coloringbook.listeners.OnPageSelectedListener
import com.personal.coloringbook.models.ColoringPage

class ColoringPageSelectionActivity : AppCompatActivity(), OnPageSelectedListener {

    private lateinit var gridView: GridView
    private lateinit var coloringPageAdapter: ColoringPageAdapter
    private lateinit var coloringPages: List<ColoringPage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring_page_selection)

        coloringPages = listOf(
            ColoringPage(R.drawable.coloring_page1),
            ColoringPage(R.drawable.coloring_page2),
            // Add more coloring pages as needed
        )

        gridView = findViewById(R.id.gridView)
        coloringPageAdapter = ColoringPageAdapter(this, coloringPages, this)
        gridView.adapter = coloringPageAdapter
    }

    override fun onPageSelected(position: Int) {
        val selectedPage = coloringPages[position]
        navigateToColoringPage(selectedPage)
    }

    private fun navigateToColoringPage(coloringPage: ColoringPage) {
        val intent = Intent(this, ColoringActivity::class.java)
        intent.putExtra(ColoringActivity.EXTRA_COLORING_PAGE_ID, coloringPage.drawableResId)
        startActivity(intent)
    }
}