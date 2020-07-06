package com.example.cozy.views.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet


class RoundImageView : androidx.appcompat.widget.AppCompatImageView {

    var radius = 20.0f
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    fun setRectRadius(radius: Float) {
        this.radius = radius
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val clipPath = Path()
        val rect = RectF(0F, 0F, this.width.toFloat(), this.height.toFloat())
        clipPath.addRoundRect(
            rect,
            40.0f,
            40.0f,
            Path.Direction.CW
        )
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }
}