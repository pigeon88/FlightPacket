package com.hsy.flightpacket.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.net.AHttpRequest;


/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/31 11:58.
 */
public class ErrorPageBase extends RelativeLayout {

    public static final String ERROR_TYPE_1 = "error_type_1";// 类型一：图片+一句话上
    public static final String ERROR_TYPE_2 = "error_type_2";//类型二：图片 + 一段话 + 按钮，按钮可控制有或没有

    public static int ic_smile;
    public static int ic_pity;
    public static int ic_e;
    public static int ic_cry;

    private Context context;

    //view
    private View vType1;
    private View vType2;
    private ImageView iv1, iv2;
    private TextView tv1;
    private TextView tvRefresh2;
    private TextView tv2;
    private LinearLayout content;

    public static void setConfig(int ic_smile, int ic_pity, int ic_e, int ic_cry) {
        ErrorPageBase.ic_smile = ic_smile;
        ErrorPageBase.ic_pity = ic_pity;
        ErrorPageBase.ic_e = ic_e;
        ErrorPageBase.ic_cry = ic_cry;
    }

    protected int getContentLayoutId() {
        return R.layout.view_error_page_base;
    }

    public ErrorPageBase(Context context) {
        super(context);
        this.context = context;
        initLayout();
    }

    public ErrorPageBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initLayout();
    }

    public ErrorPageBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initLayout();
    }

    public ErrorPageBase(Context context, String message) {
        super(context);
        this.context = context;
        initLayout();
        if (TextUtils.isEmpty(message)) {
            showErrorPage();
        } else {
            showErrorPage(message);
        }
    }

    public ErrorPageBase(Context context, int icResource, String message) {
        super(context);
        this.context = context;
        initLayout();
        showErrorPage(icResource, message, null, null);
    }

    public ErrorPageBase(Context context, String message, String btnText, OnClickListener listener) {
        super(context);
        this.context = context;
        initLayout();
        showErrorPage(ic_smile, btnText, message, listener);
    }

    public ErrorPageBase(Context context, int icResource, String message, String btnText, OnClickListener listener) {
        super(context);
        this.context = context;
        initLayout();
        showErrorPage(icResource, btnText, message, listener);
    }

    public ErrorPageBase(Context context, int statusCode, String responseString, OnClickListener listener) {
        super(context);
        this.context = context;
        initLayout();
        showErrorPage(statusCode, responseString, listener);
    }

    public ErrorPageBase(Context context, String type, int icResource, String btnText, String note, OnClickListener listener) {
        super(context);
        this.context = context;
        initLayout();
        showErrorPage(type, icResource, btnText, note, listener);
    }

    private void initLayout() {
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(getContentLayoutId(), null);
        vType1 = v.findViewById(R.id.v_error_type_1);
        vType2 = v.findViewById(R.id.v_error_type_2);
        content = (LinearLayout) v.findViewById(R.id.llt_view_error_page_content);
        iv1 = (ImageView) v.findViewById(R.id.iv_error_type_1);
        iv2 = (ImageView) v.findViewById(R.id.iv_error_type_2);
        tv1 = (TextView) v.findViewById(R.id.btn_error_type_1);
        tvRefresh2 = (TextView) v.findViewById(R.id.btn_error_type_2);
        tv2 = (TextView) v.findViewById(R.id.tv_error_type_2);
        vType1.setVisibility(GONE);
        vType2.setVisibility(GONE);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.setBackgroundColor(context.getResources().getColor(R.color.color_transparent));
        this.addView(v, lp);
    }

    public void showErrorPage() {
        showErrorPage(ERROR_TYPE_1, ic_smile, "没找到相关内容", null, null);
    }

    public void showErrorPage(String message) {
        showErrorPage(ERROR_TYPE_1, ic_smile, message, null, null);
    }

    public void showErrorPage(int icResource, String message) {
        showErrorPage(ERROR_TYPE_1, icResource, message, null, null);
    }

    public void showErrorPage(String message, String btnText, OnClickListener listener) {
        showErrorPage(ERROR_TYPE_2, ic_smile, btnText, message, listener);
    }

    public void showErrorPage(int icResource, String message, String btnText, OnClickListener listener) {
        showErrorPage(ERROR_TYPE_2, icResource, btnText, message, listener);
    }

    /*
    * 网络错误
    * @param statusCode:错误码
        没有网络 AHttpRequest.HTTP_CONNECT_ERR（有点击：刷新网络）
        连接超时 AHttpRequest.HTTP_TIMEOUT（有点击：刷新网络）
        400-500 错误 404（没有点击）
        500-600 连接服务器失败（没点击）
        其他：服务器有返回，但是是错误（没点击）
    * @param responseString:接口返回错误信息
    * @param listener:刷新按钮点击事件
    * */
    public void showErrorPage(int statusCode, String responseString, OnClickListener listener) {
        if (!TextUtils.isEmpty(responseString)) {
            if (statusCode == AHttpRequest.HTTP_CONNECT_ERR) {
                showErrorPage(ERROR_TYPE_2, ic_pity, "刷新网络", "当前没有网络\n点击刷新", listener);
            } else if (statusCode >= 400 && statusCode < 500) {
                showErrorPage(ERROR_TYPE_2, ic_e, null, "页面请求失败", null);
            } else if (statusCode == AHttpRequest.HTTP_TIMEOUT) {
                showErrorPage(ERROR_TYPE_2, ic_cry, "重新加载", "请求服务器超时", listener);
            } else if (statusCode >= 500 && statusCode < 600) {
                showErrorPage(ERROR_TYPE_2, ic_cry, null, "连接服务器失败", null);
            } else {
                showErrorPage(ERROR_TYPE_2, ic_cry, null, responseString, null);
            }
        } else {
            showErrorPage(ERROR_TYPE_2, ic_cry, null, "服务端返回msg为空", null);
        }

    }

    /*
    * @param type:类型, @see ERROR_TYPE_1 and ERROR_TYPE_2
    * @param icResource:显示图片的资源id
    * @param btnText:按钮上显示的字儿
    * @param note:type2时的提示文字
    * @param listener:按钮的点击事件，为null则按钮不可点击
    * */
    public void showErrorPage(String type, int icResource, String btnText, String note, OnClickListener listener) {
        this.setVisibility(View.VISIBLE);
        vType1.setVisibility(View.GONE);
        vType2.setVisibility(View.GONE);
        if (type == ERROR_TYPE_1) {
            vType1.setVisibility(View.VISIBLE);
            iv1.setImageResource(icResource);
            tv1.setText(btnText);
            if (listener == null) {
                tv1.setClickable(false);
            } else {
                tv1.setClickable(true);
                tv1.setOnClickListener(listener);
            }
            return;
        }
        if (type == ERROR_TYPE_2) {
            vType2.setVisibility(View.VISIBLE);
            iv2.setImageResource(icResource);
            tv2.setText(note);
            if (TextUtils.isEmpty(btnText)) {
                tvRefresh2.setVisibility(View.INVISIBLE);
            } else {
                tvRefresh2.setVisibility(View.VISIBLE);
                tvRefresh2.setText(btnText);
            }
            if (listener == null) {
                tvRefresh2.setClickable(false);
            } else {
                tvRefresh2.setClickable(true);
                tvRefresh2.setOnClickListener(listener);
            }
            return;
        }
    }

    public void dismiss() {
        this.setVisibility(GONE);
    }
}