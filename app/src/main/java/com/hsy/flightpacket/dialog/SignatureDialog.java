package com.hsy.flightpacket.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.views.SignatureView;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class SignatureDialog extends BaseDialogManager {

    @Bind(R.id.sv_content)
    SignatureView svContent;
    Context context;

    SureSignature signature;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_clear)
    TextView tvClear;
    @Bind(R.id.sure)
    TextView sure;

    public SignatureDialog(Context context, SureSignature signature) {
        super(context);
        this.context = context;
        this.signature = signature;
        setBottonBarGone();
        setTitleGone();
        setSubTitleGone();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.dialog_signature;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_clear, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                this.dismissDialog();
                break;
            case R.id.tv_clear:
                svContent.clear();
                break;
            case R.id.sure:
                if (!svContent.getTouched()) {
                    Toast.makeText(context, "请签名", Toast.LENGTH_SHORT).show();
                    break;
                }
                svContent.save(new SignatureView.OnSaveFinishListener() {
                    @Override
                    public void onSaveFinish(File file) {
                        if (file != null) {
                            signature.sureSignature(file.getPath());
                            dismissDialog();
                        }

                    }
                });
                break;
        }
    }


    public interface SureSignature {
        void sureSignature(String path);
    }

}
