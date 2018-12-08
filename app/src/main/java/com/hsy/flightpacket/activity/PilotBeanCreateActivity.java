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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.hsy.flightpacket.BuildConfig;
import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.dialog.CommInputTextDialog;
import com.hsy.flightpacket.util.TimeFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class PilotBeanCreateActivity extends BaseActivity {

    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.llt_bottom)
    LinearLayout lltBottom;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.pilot_bean_input_time)
    TextView pilotBeanInputTime;
    @Bind(R.id.tv_title2)
    TextView tvTitle2;
    @Bind(R.id.pilot_bean_input_name)
    EditText pilotBeanInputName;
    @Bind(R.id.title_3)
    TextView title3;
    @Bind(R.id.pilot_bean_input_model)
    EditText pilotBeanInputModel;
    @Bind(R.id.title4)
    TextView title4;
    @Bind(R.id.pilot_bean_input_flyer_num)
    EditText pilotBeanInputFlyerNum;
    @Bind(R.id.tv_title5)
    TextView tvTitle5;
    @Bind(R.id.pilot_bean_input_piloter_num)
    EditText pilotBeanInputPiloterNum;
    @Bind(R.id.tv_title7)
    TextView tvTitle7;
    @Bind(R.id.tv_choose_out_pic)
    TextView tvChooseOutPic;
    @Bind(R.id.tv_title6)
    TextView tvTitle6;
    @Bind(R.id.tv_choose_back_pic)
    TextView tvChooseBackPic;
    @Bind(R.id.tv_navi_content)
    EditText tvNaviContent;
    @Bind(R.id.tv_add_address_left)
    TextView tvAddAddressLeft;
    @Bind(R.id.tv_add_address_right)
    TextView tvAddAddressRight;
    @Bind(R.id.tv_out)
    ImageView tvOut;
    @Bind(R.id.tv_back)
    ImageView tvBack;
    @Bind(R.id.llt_left_add)
    LinearLayout lltLeftAdd;
    @Bind(R.id.llt_right_add)
    LinearLayout lltRightAdd;


    private String pathOut;
    private String pathBack;

    public static final int SCAN_OPEN_PHONE = 1;
    public static final int PHONE_CROP = 2;

    private int type;
    private Date mTime;

    private List<PilotRecordTable> recordTables = new ArrayList<>();

    private HashMap<Integer, String> leftAdd = new HashMap<Integer, String>();
    private HashMap<Integer, String> rightAdd = new HashMap<Integer, String>();

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_bean_input;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PilotBeanCreateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("创建领航记录表");
    }

    @OnClick({R.id.pilot_bean_input_time, R.id.tv_save, R.id.tv_choose_out_pic, R.id.tv_choose_back_pic, R.id.tv_add_address_left, R.id.tv_add_address_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pilot_bean_input_time:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMDHM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        pilotBeanInputTime.setText(TimeFormat.getStringYMDHM(date));
                        mTime = date;
                    }
                });
                dialog.show();
                break;
            case R.id.tv_choose_out_pic:
                type = 1;
                //小于6.0版本直接操作
                if (Build.VERSION.SDK_INT < 23) {

                    takePictures();
                } else {
                    //6.0以后权限处理
                    permissionForM();
                }
                break;
            case R.id.tv_choose_back_pic:
                type = 2;
                if (Build.VERSION.SDK_INT < 23) {

                    takePictures();
                } else {
                    //6.0以后权限处理
                    permissionForM();
                }
                break;
            case R.id.tv_add_address_left:
                CommInputTextDialog nameDialog = new CommInputTextDialog(this, new CommInputTextDialog.PlanNameListener() {
                    @Override
                    public void planName(String name) {
                        final View view = LayoutInflater.from(PilotBeanCreateActivity.this).inflate(R.layout.item_address, null);
                        TextView tv = view.findViewById(R.id.tv_des);
                        tv.setText(name);
                        final int index = lltLeftAdd.getChildCount();
                        view.setTag(index);
                        ImageView imageView = view.findViewById(R.id.iv_close);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lltLeftAdd.removeView(view);
                                leftAdd.remove(index);
                            }
                        });
                        lltLeftAdd.addView(view);
                        leftAdd.put(index, name);
                    }
                }, "请输入地点");
                nameDialog.showDialog();
                break;
            case R.id.tv_add_address_right:
                CommInputTextDialog nameDialog1 = new CommInputTextDialog(this, new CommInputTextDialog.PlanNameListener() {
                    @Override
                    public void planName(String name) {
                        final View view = LayoutInflater.from(PilotBeanCreateActivity.this).inflate(R.layout.item_address, null);
                        ImageView imageView = view.findViewById(R.id.iv_close);
                        TextView tv = view.findViewById(R.id.tv_des);
                        tv.setText(name);
                        final int index = lltRightAdd.getChildCount();
                        view.setTag(index);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lltRightAdd.removeView(view);
                                rightAdd.remove(index);
                            }
                        });
                        lltRightAdd.addView(view);
                        rightAdd.put(index, name);
                    }
                }, "请输入地点");
                nameDialog1.showDialog();
                break;
            case R.id.tv_save:
                if (mTime == null) {
                    Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pilotBeanInputName.getText())) {
                    Toast.makeText(this, "请输入名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pilotBeanInputModel.getText())) {
                    Toast.makeText(this, "请输入机型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pilotBeanInputFlyerNum.getText())) {
                    Toast.makeText(this, "请输飞行员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pilotBeanInputPiloterNum.getText())) {
                    Toast.makeText(this, "请输领航员", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if (TextUtils.isEmpty(picPathOut)) {
                    Toast.makeText(this, "请选择出航图", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(picPathBack)) {
                    Toast.makeText(this, "请选择归航图", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                if (TextUtils.isEmpty(tvNaviContent.getText())) {
                    Toast.makeText(this, "请选通讯导航资料", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (leftAdd.size() == 0) {
                    Toast.makeText(this, "请添加左表信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (leftAdd.size() == 0) {
                    Toast.makeText(this, "请添加右表信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                //保存领航记录信息
                PilotBean pilotBean = new PilotBean(null, mTime, pilotBeanInputName.getText().toString(), pilotBeanInputModel.getText().toString(), pilotBeanInputFlyerNum.getText().toString(), pilotBeanInputPiloterNum.getText().toString(), picPathOut, picPathBack, tvNaviContent.getText().toString(), null, null);
                DataBaseUtils.insertPilotBean(pilotBean);

                List<String> mapValuesListLeft = new ArrayList<>(leftAdd.values());
                //保存地址信息
                List<PilotRecordTable> pilotRecordTablesLeft = new ArrayList<>();
                for (int i = 0; i < mapValuesListLeft.size(); i++) {
                    PilotRecordTable recordTableLeft = new PilotRecordTable(null, mapValuesListLeft.get(i), null, null, 0, 0, 0, 0, 0, 0, "", pilotBean.getId(), true, "", "", "", 0);
                    pilotRecordTablesLeft.add(recordTableLeft);
                }
                //插入左表数据
                DataBaseUtils.savePilotRecord(pilotRecordTablesLeft);

                List<String> mapValuesList = new ArrayList<>(rightAdd.values());
                //保存地址信息
                List<PilotRecordTable> pilotRecordTablesRight = new ArrayList<>();
                for (int i = 0; i < mapValuesList.size(); i++) {
                    PilotRecordTable recordTableLeft = new PilotRecordTable(null, mapValuesList.get(i), null, null, 0, 0, 0, 0, 0, 0, "", pilotBean.getId(), false, "", "", "", 0);
                    pilotRecordTablesRight.add(recordTableLeft);
                }
                //插入右表数据
                DataBaseUtils.savePilotRecord(pilotRecordTablesRight);
                Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    //图片文件路径
    private String picPath;
    private String picPathOut;
    private String picPathBack;
    //图片对应Uri
    private Uri photoUri;
    //拍照对应RequestCode
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    //裁剪图片
    private static final int CROP_PICTURE = 3;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PIC_BY_TACK_PHOTO) {
                Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picPath = cursor.getString(columnIndex);  //获取照片路径
                cursor.close();
                if (picPath != null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
                    photoUri = Uri.fromFile(new File(picPath));
                    if (Build.VERSION.SDK_INT > 23) {
                        photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(picPath));
                        cropForN(picPath, CROP_PICTURE);
                    } else {
                        startPhotoZoom(photoUri, CROP_PICTURE);
                    }
                }
                if (type == 1) {
                    picPathOut = picPath;
                }
                if (type == 2) {
                    picPathBack = picPath;
                }
            }
            if (requestCode == CROP_PICTURE) {
                if (photoUri != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                    if (bitmap != null) {
                        if (type == 1) {
                            tvOut.setImageBitmap(bitmap);
                        }
                        if (type == 2) {
                            tvBack.setImageBitmap(bitmap);
                        }
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
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    //拍照按钮
    private static final int PERMISSIONS_FOR_TAKE_PHOTO = 10;

    /**
     * 安卓6.0以上版本权限处理
     */
    private void permissionForM() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
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
