package com.hsy.flightpacket.component;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.util.TextViewUtils;


/**
 * Created by xiongweimin on 2017/3/21.
 */

public class BaseNavigationBar extends FrameLayout {

    Context mContext;

    FrameLayout baseBar;
    TextView tvLeft;
    TextView tvCenter;
    TextView tvRight1;
    TextView tvRight2;
    TextView tvRight3;

    public BaseNavigationBar(Context context) {
        this(context, null);
    }

    public BaseNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View.inflate(context, R.layout.base_navigation_bar, this);
        baseBar = (FrameLayout) findViewById(R.id.fl_navigation_base);
        tvLeft = (TextView) findViewById(R.id.tv_base_navigation_left);
        tvCenter = (TextView) findViewById(R.id.tv_base_navigation_center);
        tvRight1 = (TextView) findViewById(R.id.tv_base_navigation_right1);
        tvRight2 = (TextView) findViewById(R.id.tv_base_navigation_right2);
        tvRight3 = (TextView) findViewById(R.id.tv_base_navigation_right3);
        setBackOnclick();
    }

    /**
     * 设置返回按钮点击事件 并关闭页面
     */
    public void setBackOnclick() {
        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
            }
        });
    }

    /**
     * 设置navigation bar title
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        tvCenter.setText(title);
    }


    /**
     * @param res     图片资源
     * @param text    文字显示
     * @param onclick 点击事件
     */
    public void setRightBtnOne(int res, String text, OnClickListener onclick) {
        if (onclick != null) {
            tvRight1.setVisibility(VISIBLE);
            //设置资源
            if (res != 0) {
                TextViewUtils.setDrawableLeft(tvRight1, res);
            }
            if (!TextUtils.isEmpty(text)) {
                tvRight1.setText(text);
            }
            //设置文字
            tvRight1.setOnClickListener(onclick);
        } else {
            tvRight2.setVisibility(GONE);
            tvRight3.setVisibility(GONE);
        }
    }

    /**
     * 设置两个按钮点击
     *
     * @param res1
     * @param text1
     * @param res2
     * @param text2
     * @param onClick1
     * @param onClick2
     */
    public void setRightBtnTwo(int res1, String text1, int res2, String text2, OnClickListener onClick1, OnClickListener onClick2) {
        if (onClick1 != null && onClick2 != null) {
            tvRight1.setVisibility(VISIBLE);
            tvRight2.setVisibility(VISIBLE);
            //设置资源
            if (res1 != 0) {
                TextViewUtils.setDrawableLeft(tvRight1, res1);
            }
            if (res2 != 0) {
                TextViewUtils.setDrawableLeft(tvRight2, res2);
            }
            if (!TextUtils.isEmpty(text1)) {
                tvRight1.setText(text1);
            }
            if (!TextUtils.isEmpty(text2)) {
                tvRight2.setText(text2);
            }
            //设置文字
            tvRight1.setOnClickListener(onClick1);
            tvRight2.setOnClickListener(onClick2);
        } else {
            tvRight3.setVisibility(GONE);
        }
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    /**
     * 获取右边按钮1
     */
    public TextView getTvRight1() {
        return tvRight1;
    }

    /**
     * 获取右边按钮2
     *
     * @return
     */
    public TextView getTvRight2() {
        return tvRight2;
    }

    /**
     * 获取右边按钮3
     *
     * @param tvRight3
     */
    public void setTvRight3(TextView tvRight3) {
        this.tvRight3 = tvRight3;
    }

}
