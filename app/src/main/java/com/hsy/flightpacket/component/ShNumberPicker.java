package com.hsy.flightpacket.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * 自定义NumberPicker，去掉可编辑，设置字体颜色
 * Created by Mark-Office on 2016/4/13.
 */
public class ShNumberPicker extends NumberPicker {

    public ShNumberPicker(Context context) {
        super(context);
    }

    public ShNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    public void updateView(View view) {
        if (view instanceof EditText) {
            //这里修改字体的属性
            ((EditText) view).setTextColor(Color.parseColor("#343434"));
            ((EditText) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//            ((EditText) view).setTextSize();
        }
    }
}
