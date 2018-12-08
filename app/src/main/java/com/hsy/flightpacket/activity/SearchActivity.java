package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.util.CommUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/29.
 */

public class SearchActivity extends BaseActivity {


    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.tv_search)
    ImageView tvSearch;
    @Bind(R.id.cb_choose1)
    ImageView cbChoose1;
    @Bind(R.id.tv_check1)
    ImageView tvCheck1;
    @Bind(R.id.rlt_1)
    RelativeLayout rlt1;
    @Bind(R.id.cb_choose2)
    ImageView cbChoose2;
    @Bind(R.id.tv_check2)
    ImageView tvCheck2;
    @Bind(R.id.rlt_2)
    RelativeLayout rlt2;
    @Bind(R.id.cb_choose3)
    ImageView cbChoose3;
    @Bind(R.id.tv_check3)
    ImageView tvCheck3;
    @Bind(R.id.rlt_3)
    RelativeLayout rlt3;
    @Bind(R.id.cb_choose4)
    ImageView cbChoose4;
    @Bind(R.id.tv_check4)
    ImageView tvCheck4;
    @Bind(R.id.rlt_4)
    RelativeLayout rlt4;
    @Bind(R.id.llt_search)
    LinearLayout lltSearch;
    @Bind(R.id.tv_search_logo)
    TextView searchLogo;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("视频、文档搜索");
        //键盘弹起
        CommUtils.showSoftInputFromWindow(this, edtSearch);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    lltSearch.setVisibility(View.VISIBLE);
                    searchLogo.setVisibility(View.GONE);

                    hideSoftKeyboard(edtSearch);
                    return true;
                }
                return false;
            }
        });

    }

    /**
     * 2      * 隐藏软键盘
     * 3      * @param v
     * 4
     */
    public static void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @OnClick({R.id.tv_search, R.id.rlt_1, R.id.rlt_2, R.id.rlt_3, R.id.rlt_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                lltSearch.setVisibility(View.VISIBLE);
                searchLogo.setVisibility(View.GONE);
                break;
            //TODO
          /*  case R.id.rlt_1:
                VideoPlayActivity.start(this);
                break;
            case R.id.rlt_2:
                VideoPlayActivity.start(this);
                break;*/
            case R.id.rlt_3:
                CourseDetailActivity.start(this,0);
                break;
            case R.id.rlt_4:
                CourseDetailActivity.start(this,0);
                break;
        }
    }
}
