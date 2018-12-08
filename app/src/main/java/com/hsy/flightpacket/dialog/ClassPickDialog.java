package com.hsy.flightpacket.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.PClass;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/7/11.
 */

public class ClassPickDialog extends BaseDialogManager {

    @Bind(R.id.tv_one)
    TextView tvOne;
    @Bind(R.id.tv_two)
    TextView tvTwo;
    @Bind(R.id.tv_three)
    TextView tvThree;

    private ClassCheck classCheck;

    List<PClass> mClass;

    public ClassPickDialog(Context context, ClassCheck classCheck) {
        super(context);
        this.classCheck = classCheck;
        setTitleGone();
        setSubTitleGone();
        setBottonBarGone();
        mClass = DataBaseUtils.searchPClass();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.dialog_class_pick;
    }

    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                classCheck.classChoose(mClass.get(0));
                dismissDialog();
                break;
            case R.id.tv_two:
                classCheck.classChoose(mClass.get(1));
                dismissDialog();
                break;
            case R.id.tv_three:
                classCheck.classChoose(mClass.get(2));
                dismissDialog();
                break;

        }
    }

    public interface ClassCheck {
        void classChoose(PClass pClass);
    }
}
