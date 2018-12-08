package com.hsy.flightpacket.extra;

import android.content.DialogInterface;
import android.view.View;

/**
 * 应以页面加载方法
 */
public interface ILoadingController {

    void showLoadingDialog();

    void showLoadingDialog(String message);

    void showLoadingDialog(String message, DialogInterface.OnCancelListener listener);

    void dismissLoadingDialog();

    void showInitLoading();

    void showInitLoading(String text);

    void dismissInitLoading();

    void showErrorPage();

    void showErrorPage(String message);

    void showErrorPage(int icResource, String message);

    void showErrorPage(String message, String btnText, View.OnClickListener listener);

    void showErrorPage(int statusCode, String responseString, View.OnClickListener listener);

    void showErrorPage(String type, int icResource, String btnText, String note, View.OnClickListener listener);

    //void cancelErrorPage();

    void dismissErrorPage();

    //void hiddenErrorPage();
}
