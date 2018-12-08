package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.TeachActivity;
import com.hsy.flightpacket.bean.dao.TeachActivityChild;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.views.ChildTimeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/8/9.
 */

public class TeachActivityPlanActivity extends BaseActivity {

    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.llt_bottom)
    LinearLayout lltBottom;
    @Bind(R.id.tv_title8)
    TextView tvTitle8;
    @Bind(R.id.tv_jihao)
    EditText tvJihao;
    @Bind(R.id.tv_title9)
    TextView tvTitle9;
    @Bind(R.id.tv_jizhanghao)
    EditText tvJizhanghao;
    @Bind(R.id.tv_title10)
    TextView tvTitle10;
    @Bind(R.id.rb_1)
    RadioButton rb1;
    @Bind(R.id.rb_2)
    RadioButton rb2;
    @Bind(R.id.rg_shifei)
    RadioGroup rgShifei;
    @Bind(R.id.tv_title_zhengjia)
    TextView tvTitleZhengjia;
    @Bind(R.id.tv_zhengjia)
    EditText tvZhengjia;
    @Bind(R.id.tv_title_fujia)
    TextView tvTitleFujia;
    @Bind(R.id.tv_fujia)
    EditText tvFujia;
    @Bind(R.id.tv_title11)
    TextView tvTitle11;
    @Bind(R.id.edit_lianxihao)
    EditText editLianxihao;
    @Bind(R.id.rlt_juli)
    RelativeLayout rltJuli;
    @Bind(R.id.tv_title2)
    TextView tvTitle2;
    @Bind(R.id.edit_cishu)
    EditText editCishu;
    @Bind(R.id.title_3)
    TextView title3;
    @Bind(R.id.edit_zhengjia)
    EditText editZhengjia;
    @Bind(R.id.title4)
    TextView title4;
    @Bind(R.id.edit_fujia)
    EditText editFujia;
    @Bind(R.id.tv_title5)
    TextView tvTitle5;
    @Bind(R.id.edit_gaodu)
    EditText editGaodu;
    @Bind(R.id.tv_title6)
    TextView tvTitle6;
    @Bind(R.id.edit_hangxianhao)
    EditText editHangxianhao;
    @Bind(R.id.tv_title_jiayou)
    TextView tvTitleJiayou;
    @Bind(R.id.rg_jiayou)
    RadioGroup rgJiayou;
    @Bind(R.id.llt_child)
    LinearLayout lltChild;
    @Bind(R.id.tv_add_child)
    TextView tvAddChild;
    @Bind(R.id.llt_zjfj)
    LinearLayout lltZjFj;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.rb_jiayou1)
    RadioButton radioButton1;
    @Bind(R.id.rb_jiayou2)
    RadioButton radioButton2;
    private HashMap<Integer, String> childHash = new HashMap<Integer, String>();
    private boolean isShifei;

    private List<TeachActivityChild> childrens = new ArrayList<>();

    private Long planId;

    TeachActivity teachActivity;

    @Override
    public int getContentLayoutId() {
        return R.layout.acitivity_teach_activity;
    }

    public static void start(Context context, Long planId) {
        Intent intent = new Intent(context, TeachActivityPlanActivity.class);
        intent.putExtra("plan_id", planId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("创建教学活动计划");
        ivDelete.setVisibility(View.GONE);
        planId = getIntent().getExtras().getLong("plan_id");
        rgShifei.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        //显示
                        lltZjFj.setVisibility(View.VISIBLE);
                        isShifei = true;
                        break;
                    case R.id.rb_2:
                        lltZjFj.setVisibility(View.GONE);
                        isShifei = false;
                        break;
                }
            }
        });
    }

    @OnClick({R.id.rg_shifei, R.id.tv_add_child, R.id.tv_save})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.tv_add_child:
                final ChildTimeView childTimeView = new ChildTimeView(this);
                final int index = lltChild.getChildCount();
                view.setTag(index);
                childTimeView.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TeachActivityPlanActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                        lltChild.removeView(childTimeView);
                        childHash.remove(index);
                    }
                });
                lltChild.addView(childTimeView);
                break;
            case R.id.tv_save:
                //判断是否已输入
                String jihao = tvJihao.getText().toString();
                if (TextUtils.isEmpty(jihao)) {
                    Toast.makeText(TeachActivityPlanActivity.this, "请输入机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                String jizhanghao = tvJizhanghao.getText().toString();
                if (TextUtils.isEmpty(jizhanghao)) {
                    Toast.makeText(TeachActivityPlanActivity.this, "请输入机长号", Toast.LENGTH_SHORT).show();
                    return;
                }
                String shifeizhujia = null;
                String shfeifujia = null;
                //试飞
                if (isShifei) {
                    shifeizhujia = tvZhengjia.getText().toString();
                    if (TextUtils.isEmpty(shifeizhujia)) {
                        Toast.makeText(TeachActivityPlanActivity.this, "请输入主驾编号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    shfeifujia = tvFujia.getText().toString();
                    if (TextUtils.isEmpty(shfeifujia)) {
                        Toast.makeText(TeachActivityPlanActivity.this, "请输入副驾编号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                teachActivity = new TeachActivity(null, jihao, jizhanghao, isShifei, shifeizhujia, shfeifujia, planId);
                DataBaseUtils.insertTeachActivity(teachActivity);
                //子项1
                String lianxihao = editLianxihao.getText().toString();
                String cishu = editCishu.getText().toString();
                String zhengjia = editZhengjia.getText().toString();
                String fujia = editFujia.getText().toString();
                String gaodu = editGaodu.getText().toString();
                String hangxian = editHangxianhao.getText().toString();
                boolean isCheck = radioButton1.isChecked();
                TeachActivityChild teachActivityChild = new TeachActivityChild(null, lianxihao, cishu, zhengjia, fujia, gaodu, hangxian, isCheck, teachActivity.getId());
                DataBaseUtils.insertTeachActivityChildDao(teachActivityChild);
                //请输入对应内容
                if (remindInput(lianxihao, cishu, zhengjia, fujia, gaodu, hangxian)) return;
                for (int i = 0; i < lltChild.getChildCount(); i++) {
                    ChildTimeView childTimeView1 = (ChildTimeView) lltChild.getChildAt(i);
                    String lianxihao1 = childTimeView1.editLianxihao.getText().toString();
                    String cishu1 = childTimeView1.editCishu.getText().toString();
                    String zhengjia1 = childTimeView1.editZhengjia.getText().toString();
                    String fujia1 = childTimeView1.editFujia.getText().toString();
                    String gaodu1 = childTimeView1.editGaodu.getText().toString();
                    String hangxian1 = childTimeView1.editHangxianhao.getText().toString();
                    boolean isCheck1 = childTimeView1.radioButton1.isChecked();
                    if (remindInput(lianxihao1, cishu1, zhengjia1, fujia1, gaodu1, hangxian1))
                        return;
                    //通过了的话直接下一步
                    TeachActivityChild teachActivityChild1 = new TeachActivityChild(null, lianxihao1, cishu1, zhengjia1, fujia1, gaodu1, hangxian1, isCheck1, teachActivity.getId());
                    DataBaseUtils.insertTeachActivityChildDao(teachActivityChild1);
                }
                finish();
                break;
        }
    }

    /**
     * 请输入对应内容
     *
     * @param lianxihao
     * @param cishu
     * @param zhengjia
     * @param fujia
     * @param gaodu
     * @param hangxian
     * @return
     */
    private boolean remindInput(String lianxihao, String cishu, String zhengjia, String fujia, String gaodu, String hangxian) {
        if (TextUtils.isEmpty(lianxihao) || TextUtils.isEmpty(cishu) || TextUtils.isEmpty(zhengjia) || TextUtils.isEmpty(fujia) || TextUtils.isEmpty(gaodu) || TextUtils.isEmpty(hangxian)) {
            Toast.makeText(TeachActivityPlanActivity.this, "请输入对应内容", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


}
