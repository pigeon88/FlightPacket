package com.hsy.flightpacket.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hsy.flightpacket.R

class StudentTrainingDrawView : View {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 创建画笔
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.color = resources.getColor(R.color.divider_horizontal_gray)// 设置红色

        p.strokeWidth = resources.getDimensionPixelSize(R.dimen.divider_height).toFloat()

        var newWidth = width + 2
        var newHeight = height + 0

        canvas.drawLine(0f, 0f, newWidth.toFloat(), newHeight.toFloat(), p)
        canvas.drawLine(0f, 0f, newWidth.toFloat(), (newHeight * 2 / 7).toFloat(), p)
        canvas.drawLine(0f, 0f, newWidth.toFloat(), (newHeight * 4 / 7).toFloat(), p)

        canvas.drawLine(0f, (newHeight / 2).toFloat(), newWidth.toFloat(), newHeight.toFloat(), p)
    }
}
