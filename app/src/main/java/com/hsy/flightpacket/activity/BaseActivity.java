package com.hsy.flightpacket.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.application.FlightApplication;
import com.hsy.flightpacket.component.BaseNavigationBar;
import com.hsy.flightpacket.dialog.LoadingDialog;
import com.hsy.flightpacket.extra.ILoadingController;
import com.hsy.flightpacket.views.ErrorPageBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by xiongweimin on 2018/6/21.
 */

public abstract class BaseActivity extends Activity implements ILoadingController {

    protected ViewGroup mRootView;

    BaseNavigationBar baseNavigationBar;

    LinearLayout contentLayout;

    LinearLayout loadingPanel;

    ErrorPageBase errorPageBase;

    LoadingDialog loadingDialog;

    private TextView loadingText;

    private View contentView;

    private boolean destroyed = false;

    public abstract int getContentLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlightApplication.getInstance().addActivity(this);
        //设置布局方式
        setContentView(R.layout.activity_base);
        initViews();
    }

    /**
     * 初始化base views
     */
    public void initViews() {
        mRootView = (ViewGroup) findViewById(R.id.rootView);
        baseNavigationBar = (BaseNavigationBar) findViewById(R.id.bnb_base_navigation);
        contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
        loadingPanel = (LinearLayout) findViewById(R.id.loadingPanel);
        errorPageBase = (ErrorPageBase) findViewById(R.id.error_page_base);
        loadingText = (TextView) findViewById(R.id.loadingText);
        int contentResID = getContentLayoutId();
        if (contentResID > 0) {
            contentView = getLayoutInflater().inflate(contentResID, null);
            ButterKnife.bind(this, contentView);
            contentLayout.addView(contentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        baseNavigationBar.setTitle(title);
    }
    /**
     * 获取 navigation bar
     *
     * @return
     */
    public BaseNavigationBar getBaseNavigationBar() {
        return baseNavigationBar;
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog("正在加载...");
    }
    /**
     * 显示加载对话框
     *
     * @param message 自定义文字
     */
    @Override
    public void showLoadingDialog(String message) {
        dismissLoadingDialog();
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.setTextContent(message);
        loadingDialog.showDialog();
    }

    @Override
    public void showLoadingDialog(String message, DialogInterface.OnCancelListener listener) {

    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing() && !destroyed) {
            loadingDialog.dismissDialog();
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
        FlightApplication.getInstance().removeActivity(this);
        ButterKnife.unbind(this);
    }

    /**
     * 全屏消失页面显示加载进度
     */
    @Override
    public void showInitLoading() {
        //显示全屏加载空白
        showInitLoading("正在加载中...");
    }

    @Override
    public void showInitLoading(String text) {
        //显示全屏空白加载 带文字
        dismissErrorPage();
        contentView.setVisibility(View.GONE);
        loadingPanel.setVisibility(View.VISIBLE);
        loadingText.setText(text);
    }

    @Override
    public void dismissInitLoading() {
        //消失全屏空白
        contentView.setVisibility(View.VISIBLE);
        loadingPanel.setVisibility(View.GONE);
    }


    @Override
    public void showErrorPage() {
        //显示错误页面
        errorPageBase.showErrorPage();
    }

    @Override
    public void showErrorPage(String message) {
        //显示带消息的错误页面
        setContentLoadingGone();
        errorPageBase.showErrorPage(message);
    }

    @Override
    public void showErrorPage(int icResource, String message) {
        //显示错误页面带图片和文字
        setContentLoadingGone();
        errorPageBase.showErrorPage(icResource, message);
    }

    @Override
    public void showErrorPage(String message, String btnText, View.OnClickListener listener) {
        //显示错误页面带图片文字和点击事件
        setContentLoadingGone();
        errorPageBase.showErrorPage(message, btnText, listener);
    }

    @Override
    public void showErrorPage(int statusCode, String responseString, View.OnClickListener listener) {
        //显示错误页面带状态码，带服务端错误返回
        setContentLoadingGone();
        errorPageBase.showErrorPage(statusCode, responseString, listener);
    }

    @Override
    public void showErrorPage(String type, int icResource, String btnText, String note, View.OnClickListener listener) {
        //显示错误页面带图片带按钮文字，带描述，带按钮点击事件
        setContentLoadingGone();
        errorPageBase.showErrorPage(type, icResource, btnText, note, listener);
    }

    /**
     * 隐藏界面内容
     */
    public void setContentLoadingGone() {
        loadingPanel.setVisibility(View.GONE);
        contentLayout.setVisibility(View.GONE);
    }


    @Override
    public void dismissErrorPage() {
        contentLayout.setVisibility(View.VISIBLE);
        errorPageBase.setVisibility(View.GONE);
    }

}
