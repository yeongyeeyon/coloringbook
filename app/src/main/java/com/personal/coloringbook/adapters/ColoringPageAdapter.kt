package com.personal.coloringbook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.personal.coloringbook.R
import com.personal.coloringbook.listeners.OnPageSelectedListener
import com.personal.coloringbook.models.ColoringPage

class ColoringPageAdapter(
    private val context: Context,
    private val coloringPages: List<ColoringPage>,
    private val onPageSelectedListener: OnPageSelectedListener
) : BaseAdapter() {

    override fun getCount(): Int {
        return coloringPages.size
    }

    override fun getItem(position: Int): ColoringPage {
        return coloringPages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_coloring_page, parent, false
        )

        val coloringPage = getItem(position)
        val coloringImageView = view.findViewById<ImageView>(R.id.coloringImageView)
        coloringImageView.setImageResource(coloringPage.drawableResId)

        view.setOnClickListener {
            onPageSelectedListener.onPageSelected(position)
        }

        return view
    }
}