package com.hsy.flightpacket.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiongweimin on 2018/1/31.
 */

public class GobildTextView extends TextView {

    //数字
    public GobildTextView(Context context) {
        super(context);
        init(context);
        // TODO Auto-generated constructor stub
    }

    public GobildTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        // TODO Auto-generated constructor stub
    }

    public GobildTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        // TODO Auto-generated constructor stub
    }

    private void init(Context context) {
        // TODO Auto-generated method stub
        AssetManager aManager = context.getAssets();
        Typeface font = Typeface.createFromAsset(aManager, "font/Gobold-Regular.ttf");
        setTypeface(font);
    }
}