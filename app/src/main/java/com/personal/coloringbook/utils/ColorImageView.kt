package com.personal.coloringbook.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView


class ColoringImageView : AppCompatImageView {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mX = 0f
    private var mY = 0f

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.color = -0x10000
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeWidth = 12f
        mPath = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath!!, mPaint!!)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                return true
            }

            MotionEvent.ACTION_UP -> {
                touchUp()
                performClick() // Call performClick to handle the click event
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun touchStart(x: Float, y: Float) {
        mPath!!.reset()
        mPath!!.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath!!.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
            invalidate()
        }
    }

    private fun touchUp() {
        mPath!!.lineTo(mX, mY)
        invalidate()
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }
}