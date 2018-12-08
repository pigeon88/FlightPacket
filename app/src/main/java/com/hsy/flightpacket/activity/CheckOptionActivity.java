package com.hsy.flightpacket.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.BuildConfig;
import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.CheckStaus;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.db.DataBaseUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/22.
 */

public class CheckOptionActivity extends BaseActivity {


    //拍照按钮
    private static final int PERMISSIONS_FOR_TAKE_PHOTO = 10;

    private static final String FLIGHT_CHECK_ID = "flight_check_id";
    @Bind(R.id.iv_cancel)
    ImageView ivCancel;
    @Bind(R.id.llt_bottom)
    LinearLayout lltBottom;
    //图片文件路径
    private String picPath;
    //图片对应Uri
    private Uri photoUri;
    //拍照对应RequestCode
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    //裁剪图片
    private static final int CROP_PICTURE = 3;

    @Bind(R.id.iv_photo)
    ImageView ivPhoto;
    @Bind(R.id.tv_rephoto)
    TextView tvRephoto;
    @Bind(R.id.tv_done)
    TextView tvDone;
    @Bind(R.id.des)
    TextView tvDes;


    private boolean hasPhoto;

    private FlightCheck flightCheck;

    public CheckOptionActivity() {
    }

    public static void start(Context context, Long id) {
        Intent intent = new Intent(context, CheckOptionActivity.class);
        intent.putExtra(FLIGHT_CHECK_ID, id);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_check_option;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flightCheck = DataBaseUtils.loadFlightCheckById(getIntent().getLongExtra(FLIGHT_CHECK_ID, 0));
        if (flightCheck != null) {
            getBaseNavigationBar().setTitle(flightCheck.getTitle());
            tvDes.setText(flightCheck.getDes());
            if (flightCheck.getPicUrl() != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(flightCheck.getPicUrl());
                if (bitmap != null) {
                    ivPhoto.setImageBitmap(bitmap);
                }
            } else {
                ivCancel.setVisibility(View.INVISIBLE);
                ivPhoto.setImageResource(R.drawable.zhunbei_paizhao);
                lltBottom.setVisibility(View.GONE);
            }
        }

    }


    @OnClick({R.id.iv_cancel, R.id.iv_photo, R.id.tv_rephoto, R.id.tv_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
            case R.id.tv_rephoto:
                //小于6.0版本直接操作
                if (Build.VERSION.SDK_INT < 23) {
                    takePictures();
                } else {
                    //6.0以后权限处理
                    permissionForM();
                }
                break;
            case R.id.tv_done:
                if (hasPhoto) {
                    finish();
                } else {
                    Toast.makeText(this, "请上传照片", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_cancel:
                ivCancel.setVisibility(View.INVISIBLE);
                ivPhoto.setImageResource(R.drawable.zhunbei_paizhao);
                lltBottom.setVisibility(View.GONE);
                flightCheck.setPicUrl(null);
                flightCheck.setIsChecked(false);
                DataBaseUtils.correctFlightCheck(flightCheck);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PIC_BY_TACK_PHOTO) {
                String[] pojo = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
                if (cursor != null) {
                    int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
                    cursor.moveToFirst();
                    picPath = cursor.getString(columnIndex);
                    if (Build.VERSION.SDK_INT < 14) {
                        cursor.close();
                    }
                }
                if (picPath != null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
                    photoUri = Uri.fromFile(new File(picPath));
                    if (Build.VERSION.SDK_INT > 23) {
                        //photoUri = FileProvider.getUriForFile(this, "com.innopro.bamboo.fileprovider", new File(picPath));
                        photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(picPath));
                        cropForN(picPath, CROP_PICTURE);
                    } else {
                        startPhotoZoom(photoUri, CROP_PICTURE);
                    }
                } else {
                    //错误提示
                }
            }
            if (requestCode == CROP_PICTURE) {
                if (photoUri != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                    if (bitmap != null) {
                        hasPhoto = true;
                        ivPhoto.setImageBitmap(bitmap);
                        ivCancel.setVisibility(View.VISIBLE);
                        lltBottom.setVisibility(View.VISIBLE);
                        flightCheck.setPicUrl(picPath);
                        flightCheck.setIsChecked(true);
                        DataBaseUtils.correctFlightCheck(flightCheck);
                    }
                }
            }
        }
    }

    /**
     * 拍照获取图片
     */
    private void takePictures() {
        //执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        } else {
            Toast.makeText(this, "手机未插入内存卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 图片裁剪，参数根据自己需要设置
     *
     * @param uri
     * @param REQUE_CODE_CROP
     */
    private void startPhotoZoom(Uri uri,
                                int REQUE_CODE_CROP) {
        int dp = 500;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);//输出是X方向的比例
        intent.putExtra("aspectY", 3);
        intent.putExtra("outputX", 600);//输出X方向的像素
        intent.putExtra("outputY", 450);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);//设置为不返回数据
        startActivityForResult(intent, REQUE_CODE_CROP);
    }

    /**
     * 7.0以上版本图片裁剪操作
     *
     * @param imagePath
     * @param REQUE_CODE_CROP
     */
    private void cropForN(String imagePath, int REQUE_CODE_CROP) {
        Uri cropUri = getImageContentUri(new File(imagePath));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(cropUri, "image/*");
        intent.putExtra("crop", "true");
        //输出是X方向的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 450);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUE_CODE_CROP);
    }

    private Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    /**
     * 安卓6.0以上版本权限处理
     */
    private void permissionForM() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_FOR_TAKE_PHOTO);
        } else {
            takePictures();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_FOR_TAKE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictures();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
