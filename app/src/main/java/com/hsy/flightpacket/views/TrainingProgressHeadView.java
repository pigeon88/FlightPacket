package com.hsy.flightpacket.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hsy.flightpacket.R;


/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/24 16:59.
 */
public class TrainingProgressHeadView extends FrameLayout {

    public TrainingProgressHeadView(Context context) {
        super(context);
        init(context);
    }

    public TrainingProgressHeadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public TrainingProgressHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.report_student_training_progress_head_ctrl, this);
    }
}
