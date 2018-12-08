package com.hsy.flightpacket.views;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiongweimin on 2018/8/9.
 */

public class ChildTimeView extends LinearLayout {

    public EditText editLianxihao;
    public EditText editCishu;
    public EditText editZhengjia;
    public EditText editFujia;
    public EditText editGaodu;
    public EditText editHangxianhao;
    public RadioGroup rgJiayou;
    public ImageView imageView;
    private Context mContext;
    public RadioButton radioButton1;
    public RadioButton radioButton2;

    public ChildTimeView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ChildTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.child_item, null);
        editLianxihao = view.findViewById(R.id.edit_lianxihao);
        editCishu = view.findViewById(R.id.edit_cishu);
        editZhengjia = view.findViewById(R.id.edit_zhengjia);
        editFujia = view.findViewById(R.id.edit_fujia);
        editGaodu = view.findViewById(R.id.edit_gaodu);
        editHangxianhao = view.findViewById(R.id.edit_hangxianhao);
        rgJiayou = view.findViewById(R.id.rg_jiayou);
        imageView = view.findViewById(R.id.iv_delete);
        radioButton1 = view.findViewById(R.id.rb_jiayou1);
        radioButton2 = view.findViewById(R.id.rb_jiayou2);
        addView(view);
    }

}
