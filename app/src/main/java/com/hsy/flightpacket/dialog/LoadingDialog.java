package com.hsy.flightpacket.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.util.ScreenUtils;

/**
 * Created by xiongweimin on 2017/4/19.
 */

public class LoadingDialog {

    RelativeLayout base;
    Context mContext;
    TextView tvDes;
    ImageView ivProgress;
    Dialog dialog;
    String text;

    public LoadingDialog(Context context) {
        this.mContext = context;
        initViews(true);
    }

    public LoadingDialog(Context context, boolean cancelAble) {
        this.mContext = context;
        initViews(cancelAble);
    }


    /**
     * 初始化
     */
    public void initViews(boolean cancelAble) {
        base = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
        tvDes = (TextView) base.findViewById(R.id.tv_loading_text);
        ivProgress = (ImageView) base.findViewById(R.id.iv_progress);
        //创建dialog
        dialog = new Dialog(mContext, R.style.app_dialog);
        dialog.setContentView(base);
        dialog.setCanceledOnTouchOutside(cancelAble);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(mContext) / 4;   //设置宽度充满屏幕
        lp.height = ScreenUtils.getScreenWidth(mContext) / 4;
        window.setAttributes(lp);
    }

    /**
     * 设置
     */
    public void setTextContent(String text) {
        if (tvDes != null) {
            tvDes.setText(TextUtils.isEmpty(text) ? "" : text);
        }
    }

    /**
     * 是否显示
     *
     * @return
     */
    public boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    /**
     * 显示dialog
     */
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    mContext, R.anim.dialog_loading);
            // 使用ImageView显示动画
            ivProgress.startAnimation(hyperspaceJumpAnimation);
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
