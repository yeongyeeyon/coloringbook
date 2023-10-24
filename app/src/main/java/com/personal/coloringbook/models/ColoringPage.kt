package com.personal.coloringbook.models

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcel
import android.os.Parcelable

class ColoringPage(var drawableResId: Int) : Parcelable {
    private lateinit var originalImage: Bitmap
    private lateinit var coloredImage: Bitmap

    constructor(parcel: Parcel) : this(parcel.readInt())

    fun loadImages(resources: Resources) {
        val options = BitmapFactory.Options()
        options.inScaled = false
        originalImage = BitmapFactory.decodeResource(resources, drawableResId, options)
        coloredImage = originalImage.copy(originalImage.config, true)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(drawableResId)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getColoredImage(): Bitmap {
        return coloredImage
    }

    companion object CREATOR : Parcelable.Creator<ColoringPage> {
        override fun createFromParcel(parcel: Parcel): ColoringPage {
            return ColoringPage(parcel)
        }

        override fun newArray(size: Int): Array<ColoringPage?> {
            return arrayOfNulls(size)
        }
    }
}