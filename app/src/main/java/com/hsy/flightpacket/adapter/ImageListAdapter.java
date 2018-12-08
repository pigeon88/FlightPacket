package com.hsy.flightpacket.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/18.
 */

public class ImageListAdapter extends ViewHolderBase<String> {

    @Bind(R.id.iv_pic)
    ImageView ivPic;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_pic, null);
    }

    @Override
    public void showData(int position, String imagePic) {
        if (imagePic != null) {
            ivPic.setImageBitmap(BitmapFactory.decodeFile(imagePic));
        }
    }
}
