package com.hsy.flightpacket.activity.UtilsActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.MyImageAdapter;
import com.hsy.flightpacket.adapter.MyImagePathAdapter;
import com.hsy.flightpacket.adapter.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class PhotoViewActivity extends AppCompatActivity {
    public static final String KEY_COURSE_PIC_TYPE = "key_course_pic_type";
    public static final String KEY_COURSE_PIC = "key_course_pic";
    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private MyImagePathAdapter myImagePathAdapter;
    private TextView mTvImageCount;
    private TextView mTvSaveImage;
    private List<String> paths;
    private List<Integer> Urls;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        initView();
        initData();
    }

  /*  public static void start(Context context, ArrayList<String> urls, int index) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra("urls", urls);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }*/

    public static void start(Context context, ArrayList<Integer> urls, int index, int type) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putIntegerArrayListExtra("urls", urls);
        intent.putExtra("index", index);
        intent.putExtra(KEY_COURSE_PIC_TYPE, type);
        context.startActivity(intent);
    }

    public static void startPics(Context context, ArrayList<String> urls, int index, int type) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putStringArrayListExtra(KEY_COURSE_PIC, urls);
        intent.putExtra("index", index);
        intent.putExtra(KEY_COURSE_PIC_TYPE, type);
        context.startActivity(intent);
    }

    private void initView() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_image_count);
        /*mTvSaveImage = (TextView) findViewById(R.id.tv_save_image_photo);
        mTvSaveImage.setOnClickListener(this);*/

    }

    /**
     * 获取数据设置图片显示
     */
    private void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("index", 0);
//        Urls = getIntent().getStringArrayListExtra("urls");
        Urls = getIntent().getIntegerArrayListExtra("urls");
        type = getIntent().getIntExtra(KEY_COURSE_PIC_TYPE, 0);
        paths = getIntent().getStringArrayListExtra(KEY_COURSE_PIC);
        if (type == 1) {
            myImagePathAdapter = new MyImagePathAdapter(paths, this);
            mViewPager.setOffscreenPageLimit(1);
            mViewPager.setAdapter(myImagePathAdapter);
            mViewPager.setCurrentItem(currentPosition, false);
            mTvImageCount.setText(currentPosition + 1 + "/" + paths.size());
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    currentPosition = position;
                    mTvImageCount.setText(currentPosition + 1 + "/" + paths.size());
                }
            });

        } else {
            adapter = new MyImageAdapter(Urls, this);
            mViewPager.setAdapter(adapter);
            mViewPager.setCurrentItem(currentPosition, false);
            mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    currentPosition = position;
                    mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
                }
            });
        }


    }

    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save_image_photo:
                break;
        }
    }*/
}
