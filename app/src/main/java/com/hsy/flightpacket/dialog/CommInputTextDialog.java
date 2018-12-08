package com.hsy.flightpacket.dialog;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class CommInputTextDialog extends BaseDialogManager {

    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.sure)
    TextView sure;
    @Bind(R.id.edt_check_plan_name)
    EditText edtCheckPlanName;
    @Bind(R.id.tv_title_name)
    TextView tvTitleName;
    private Context context;
    private PlanNameListener nameListener;


    public CommInputTextDialog(Context context, PlanNameListener nameListener, String title) {
        super(context);
        this.context = context;
        this.nameListener = nameListener;
        setBottonBarGone();
        setTitleGone();
        setSubTitleGone();
        tvTitleName.setText(title);
        edtCheckPlanName.setHint(title);
    }

    public CommInputTextDialog(Context context) {
        super(context);

    }


    @Override
    public int getContentLayoutId() {
        return R.layout.dialog_check_name;
    }

    @OnClick({R.id.tv_cancel, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismissDialog();
                break;
            case R.id.sure:
                String name = edtCheckPlanName.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    if (nameListener != null) {
                        nameListener.planName(name);
                        dismissDialog();
                    }
                } else {
                    Toast.makeText(context, "请输入名称", Toast.LENGTH_SHORT);
                }
                break;
        }
    }


    public interface PlanNameListener {
        void planName(String name);
    }

}
