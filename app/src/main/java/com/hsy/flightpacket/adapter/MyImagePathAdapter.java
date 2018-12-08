package com.hsy.flightpacket.adapter;

import android.graphics.BitmapFactory;
import android.studio.view.widget.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hsy.flightpacket.photoview.PhotoView;

import java.util.List;

/**
 * Created by xiongweimin on 2018/7/17.
 */

public class MyImagePathAdapter extends PagerAdapter {
    public static final String TAG = MyImageAdapter.class.getSimpleName();
    //private List<String> imageUrls;
    private AppCompatActivity activity;
    private List<String> imageUrls;

    /*   public MyImageAdapter(List<String> imageUrls, AppCompatActivity activity) {
           this.imageUrls = imageUrls;
           this.activity = activity;
       }*/
    public MyImagePathAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        String url = imageUrls.get(position);
        String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
        //GlideManager.loadCrop(activity, url, photoView);
        photoView.setImageBitmap(BitmapFactory.decodeFile(url));
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                activity.finish();
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
