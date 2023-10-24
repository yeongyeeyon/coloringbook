package com.personal.coloringbook.fragments


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.personal.coloringbook.R
import com.personal.coloringbook.models.ColoringPage
import com.personal.coloringbook.utils.ColoringImageView


class ColoringActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COLORING_PAGE_ID = "extra_coloring_page_id"
    }

    private lateinit var coloringPage: ColoringPage
    private lateinit var coloringImageView: ColoringImageView
    private lateinit var colorPaletteLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        coloringImageView = findViewById(R.id.coloringImageView)
        colorPaletteLayout = findViewById(R.id.colorPaletteLayout)

        val extras = intent.extras
        if (extras != null && extras.containsKey(EXTRA_COLORING_PAGE_ID)) {
            val coloringPageId = extras.getInt(EXTRA_COLORING_PAGE_ID)
            coloringPage = ColoringPage(coloringPageId)
            coloringPage.loadImages(resources)
            displayColoringPage()
        } else {
            finish() // Handle error case or invalid intent
        }

        val colorArray = resources.getIntArray(R.array.color_array)

        for (colorValue in colorArray) {
            val colorButton = Button(this)
            colorButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            colorButton.setBackgroundColor(colorValue)
            colorButton.setOnClickListener {

                coloringImageView.setOnClickListener { view ->
                    // Handle click event here
                    coloringImageView.performClick() // Optional, if you want to simulate a click
                }
            }
            colorPaletteLayout.addView(colorButton)
        }
    }

    private fun displayColoringPage() {
        val coloredImage = coloringPage.getColoredImage()
        coloringImageView.setImageBitmap(coloredImage)
    }

    private fun onColorSelected(view: View) {

    }



    fun onSaveClicked(view: View) {
        // Handle save button click logic
    }

    fun onResetClicked(view: View) {
        // Handle reset button click logic
    }
}