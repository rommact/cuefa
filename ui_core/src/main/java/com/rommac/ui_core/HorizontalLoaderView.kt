package com.rommac.ui_core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap

class HorizontalLoaderView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private var thirdImageRes: VectorDrawable
    private var secondImageRes: VectorDrawable
    private var firstImageRes: VectorDrawable
    private lateinit var firstImage: Bitmap
    private lateinit var secondImage: Bitmap
    private lateinit var thirdImage: Bitmap
    private var centerX: Int = 0
    private var centerY: Int = 0
    private val paint: Paint

    private var marginY = 0f

    private var step = 20
    private lateinit var images: List<Image>

    private var state: State = State.FROM_START_TO_RIGHT


    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LoaderView,
            0, 0
        )
        try {
            firstImageRes =
                a.getDrawable(R.styleable.LoaderView_first_image) as VectorDrawable
            secondImageRes =
                a.getDrawable(R.styleable.LoaderView_second_image) as VectorDrawable
            thirdImageRes =
                a.getDrawable(R.styleable.LoaderView_third_image) as VectorDrawable

        } finally {
            a.recycle()
        }

        paint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 30f
        }

        images = listOf(
            Image(centerX - firstImage.width, firstImage),
            Image(centerX, secondImage),
            Image(centerX + thirdImage.width, thirdImage)
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (state) {
            State.FROM_START_TO_RIGHT -> {
                if (images[0].currentX < width) {
                    moveToRight(images[0])
                } else if (images[1].currentX < width) {
                    moveToRight(images[1])
                } else {
                    moveToRight(images[2])
                }
                if (allIsAbroadRight()) {
                    images.forEach { it.currentX = it.startX - width }
                    state = State.FROM_LEFT_TO_START
                }
            }
            State.FROM_LEFT_TO_START -> {
                if (!images[0].inStart()) {
                    moveToStart(images[0])
                } else if (!images[1].inStart()) {
                    moveToStart(images[1])
                } else {
                    moveToStart(images[2])
                }

                if (allInStart()) {
                    state = State.FROM_START_TO_RIGHT
                }
            }
        }
        images.forEach { draw(canvas, it) }
        invalidate()
    }

    private fun isAbroadRight(image: Image) = image.currentX >= width
    private fun allIsAbroadRight(): Boolean = images.find { !isAbroadRight(it) } == null
    private fun allInStart(): Boolean = images.find { !it.inStart() } == null

    private fun moveToRight(image: Image) {
        moveTo(image, width)
    }

    private fun moveToStart(image: Image) {
        moveTo(image, image.startX)
    }

    private fun moveTo(image: Image, toX: Int) {
        if (image.currentX + step >= toX) {
            image.currentX = toX
            return
        }
        image.currentX = image.currentX + step
    }

    private fun draw(canvas: Canvas, image: Image) =
        canvas.drawBitmap(image.bitmap, image.currentX.toFloat(), marginY, paint)


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        centerX = width / 2
        centerY = height / 2

        val scaledWith = if (width > height) width / 8 else height / 8
        firstImage = firstImageRes.toBitmap(scaledWith, scaledWith)
        secondImage = secondImageRes.toBitmap(scaledWith, scaledWith)
        thirdImage = thirdImageRes.toBitmap(scaledWith, scaledWith)

        marginY = (centerY.toFloat() - firstImage.height)

        images[0].bitmap = firstImage
        images[0].startX = centerX - firstImage.width

        images[1].bitmap = secondImage
        images[1].startX = centerX

        images[2].bitmap = thirdImage
        images[2].startX = centerX + thirdImage.width

    }

    class Image(
        var startX: Int = 0,
        var bitmap: Bitmap
    ) {
        var currentX: Int = startX
        fun inStart(): Boolean {
            if (startX == currentX) {

                return true
            }
            return false
        }
    }

    enum class State {
        FROM_START_TO_RIGHT, FROM_LEFT_TO_START
    }

}