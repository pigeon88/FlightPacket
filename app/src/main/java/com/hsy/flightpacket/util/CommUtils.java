package com.hsy.flightpacket.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.studio.os.LogCat;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;

import com.hsy.flightpacket.application.FlightApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweimin on 2017/3/23.
 */

public class CommUtils {
    private static final String TYPE_FILE = "file:";

    /**
     * 通过URI获取图片绝对路径
     *
     * @param contentUri
     * @return
     */
    public static String getAbsoluteImagePath(Context context, Uri contentUri) {
        if (StringUtil.parseStr(contentUri).startsWith(TYPE_FILE)) {
            return StringUtil.parseStr(contentUri).replace(TYPE_FILE, "");
        }
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        LogCat.i("传入的图片地址：%s", contentUri.toString());
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    /**
     * 获取文件路径
     *
     * @param Path 文件夹路径
     * @return
     */
    public static List<String> initFilePaths(String Path) {
        List<String> list = new ArrayList<>();
        File appDir = new File(Environment.getExternalStorageDirectory(), Path);
        if (!appDir.exists()) {
            appDir.mkdir();
        } else {
            File[] files = new File(appDir.getPath()).listFiles();
            for (int i = 0; i < files.length; i++) {
                list.add(files[i].getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * 获取文件名
     *
     * @return
     */
    public static String getFileNames(String name) {
        return name.substring(name.lastIndexOf("/") + 1, name.length());
    }

    /**
     * 保存一张图片。
     *
     * @param path 保存图片的路径。如：/mnt/sdcard/abc/abc.jpg
     * @throws IOException
     */
    public static boolean saveBitmap(Bitmap bitmap, String path) throws IOException {
        if (bitmap == null) return false;
        File file = new File(path);
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (fos != null) fos.close();
        }
        return true;
    }
}
