package com.hsy.flightpacket.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.hsy.flightpacket.R
import kotlinx.android.synthetic.main.report_student_training_progress_item_ctrl.view.*


/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/24 16:59.
 */
class TrainingProgressTextView : LinearLayout {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttrs: Int = 0) {
        orientation = VERTICAL
        View.inflate(context, R.layout.report_student_training_progress_item_ctrl, this)

        if (attrs != null) {
            var textUp: CharSequence? = null
            var textDown: CharSequence? = null

            val a = context.obtainStyledAttributes(attrs, R.styleable.TrainingProgressTextView, defStyleAttrs, 0)
            val n = a.indexCount
            for (i in 0..n) {
                val attr = a.getIndex(i)
                when (attr) {
                    R.styleable.TrainingProgressTextView_textUp -> textUp = a.getString(attr)
                    R.styleable.TrainingProgressTextView_textDown -> textDown = a.getString(attr)
                }
            }

            a.recycle()

            setText(textUp, textDown)
        }
    }

    fun setText(textUp: CharSequence?, textDown: CharSequence?) {
        tv_text1.text = textUp
        tv_text2.text = textDown
    }
}
