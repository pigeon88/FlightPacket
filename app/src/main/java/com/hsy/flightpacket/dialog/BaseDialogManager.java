package com.hsy.flightpacket.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hsy.flightpacket.R;
import com.hsy.flightpacket.util.ScreenUtils;

import butterknife.ButterKnife;


/**
 * Created by xiongweimin on 2017/4/13.
 */

public abstract class BaseDialogManager {

    public abstract int getContentLayoutId();

    private Context mContext;

    private ViewGroup contentLayout;

    private View contentView;

    TextView tvTitle;

    TextView tvTitleSub;


    TextView tvCancel;

    TextView tvSure;
    View tvSplit;

    Dialog dialog;

    LinearLayout base;

    LinearLayout lltSureCancel;

    ImageView ivProgress;

    public BaseDialogManager(Context context) {
        this.mContext = context;
        initViews(context);
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void initViews(Context context) {
        base = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_base, null);
        tvTitle = (TextView) base.findViewById(R.id.tv_title);
        tvTitleSub = (TextView) base.findViewById(R.id.tv_title_sub);
        tvCancel = (TextView) base.findViewById(R.id.tv_cancel);
        tvSplit = base.findViewById(R.id.tv_split);
        tvSure = (TextView) base.findViewById(R.id.tv_sure);
        lltSureCancel = (LinearLayout) base.findViewById(R.id.llt_sure_cancel);
        contentLayout = (LinearLayout) base.findViewById(R.id.llt_content);
        ivProgress = (ImageView) base.findViewById(R.id.iv_progress);
        int contentResID = getContentLayoutId();
        if (contentResID > 0) {
            contentView = ((Activity) mContext).getLayoutInflater().inflate(contentResID, null);
            ButterKnife.bind(this, contentView);
            contentLayout.addView(contentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        //创建dialog
        dialog = new Dialog(context, R.style.app_dialog);
        dialog.setContentView(base);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(context) * 4 / 5;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        //设置cancel
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dismissDialog();
                }
            }
        });
    }

    public void setTvCancelVisible(boolean visible) {
        if (!visible) {
            tvCancel.setVisibility(View.GONE);
            tvSplit.setVisibility(View.GONE);
        }
    }

    /**
     * 设置点击dialog外部是否可以取消dialg
     *
     * @param canceledOnTouchOutside
     */
    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }

    /**
     * 设置是否可以取消dialog
     *
     * @param canceled
     */
    public void setCanceled(boolean canceled) {
        if (dialog != null) {
            dialog.setCancelable(canceled);
        }
    }

    /**
     * 是否可以取消
     *
     * @param cancelAble
     */
    public void setCancelAble(boolean cancelAble) {
        if (dialog != null) {
            dialog.setCancelable(cancelAble);
        }
    }

    /**
     * 设置确定字符串
     *
     * @param str
     */
    public void setSureString(String str) {
        if (tvSure != null) {
            tvSure.setText(str);
        }
    }

    /**
     * 设置取消字符串
     *
     * @param str
     */
    public void setCancelString(String str) {
        if (tvCancel != null) {
            tvCancel.setText(str);
        }
    }

    /**
     * 开始加载
     */
    public void startloading() {
        ivProgress.setVisibility(View.VISIBLE);
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                mContext, R.anim.dialog_loading);
        // 使用ImageView显示动画
        ivProgress.startAnimation(hyperspaceJumpAnimation);
    }

    /**
     * 停止动画
     */
    public void stoploading() {
        if (ivProgress != null) {
            ivProgress.setVisibility(View.GONE);
            ivProgress.clearAnimation();
        }
    }

    /**
     * 设置确定取消消失
     */
    public void setBottonBarGone() {
        if (lltSureCancel != null) {
            lltSureCancel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题消失
     */
    public void setTitleGone() {
        if (tvTitle != null) {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置副标题消失
     */
    public void setSubTitleGone() {
        if (tvTitleSub != null) {
            tvTitleSub.setVisibility(View.GONE);
        }
    }

    /**
     * 显示dialog
     */
    public void showDialog() {
        dismissDialog();
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 设置确定之后的点击事件
     *
     * @param onClickListener
     */
    public void setSureOnClickListener(View.OnClickListener onClickListener) {
        tvSure.setOnClickListener(onClickListener);
    }

    /**
     * 取消的事件
     *
     * @param onClickListener
     */
    public void setCancelOnClickListener(View.OnClickListener onClickListener) {
        tvCancel.setOnClickListener(onClickListener);
    }

    /**
     * 设置主标题文字
     *
     * @param txt
     */
    public void setTitle(String txt) {
        if (tvTitle != null && !TextUtils.isEmpty(txt)) {
            tvTitle.setText(txt);
        }
    }

    /**
     * 设置二级标题文字
     *
     * @param txt
     */
    public void setSubTitle(String txt) {
        if (tvTitleSub != null && !TextUtils.isEmpty(txt)) {
            tvTitleSub.setText(txt);
        }
    }
}
