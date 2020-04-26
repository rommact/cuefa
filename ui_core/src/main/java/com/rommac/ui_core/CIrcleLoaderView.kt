package com.rommac.ui_core

import android.content.Context
import android.graphics.*
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap

class CIrcleLoaderView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private var firstImage: Bitmap? = null
    private var secondImage: Bitmap? = null
    private var thirdImage: Bitmap? = null
    private val firstMatrix: Matrix
    private val secondMatrix: Matrix
    private val thirdMatrix: Matrix
    private var centerX: Int = 0
    private var centerY: Int = 0
    private val paint: Paint
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        centerX = width / 2
        centerY = height / 2
        firstMatrix.setRotate(120f, centerX.toFloat(), centerY.toFloat())
        secondMatrix.setRotate(240f, centerX.toFloat(), centerY.toFloat())
        thirdMatrix.setRotate(10f)
        canvas.drawBitmap(firstImage, firstMatrix, paint)
        canvas.drawBitmap(secondImage, secondMatrix, paint)
        canvas.drawBitmap(thirdImage, thirdMatrix, paint)
    }



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        centerX = width / 2
        centerY = height / 2


        firstMatrix.setTranslate(centerX/2f, centerY/2f)
        secondMatrix.setTranslate(centerX/2f, centerY/2f)
        thirdMatrix.setTranslate(centerX/2f, centerY/2f)
    }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CIrcleLoaderView,
            0, 0
        )
        try {
            val firstImageRes =
                a.getDrawable(R.styleable.CIrcleLoaderView_first_image) as VectorDrawable
            val secondImageRes =
                a.getDrawable(R.styleable.CIrcleLoaderView_second_image) as VectorDrawable
            val thirdImageRes =
                a.getDrawable(R.styleable.CIrcleLoaderView_third_image) as VectorDrawable
            firstImage = firstImageRes.toBitmap()
            secondImage = secondImageRes.toBitmap()
            thirdImage = thirdImageRes.toBitmap()
        } finally {
            a.recycle()
        }
        firstMatrix = Matrix()
        secondMatrix = Matrix()
        thirdMatrix = Matrix()

        paint = Paint()
    }
}