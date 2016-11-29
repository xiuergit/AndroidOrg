package com.bigdata.webshell.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by xiuer on 16/8/19.
 */
public class ImageUtil {

    private Activity mActivity;

    private Uri imageUri;
    private static final int TAKE_PHOTO = 2;
    private static final int LOCAL_IMAGE_CODE = 1;

    public ImageUtil(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public final boolean checkSDcard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            WebAppUtil.toastShow(mActivity,"请插入手机存储卡再使用本功能");

        }
        return flag;
    }

    /**
     * 打开照相机或者图库的方法
     */

    public final void selectImage() {
        if (!checkSDcard())
            return;
        String[] selectPicTypeStr = {"照相机", "本地图片"};

        new AlertDialog.Builder(mActivity)
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    // 相机拍摄
                                    case 0:
                                        getImageFromCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        getImageFromAlbum();
                                        break;
                                }
                            }
                        }).show();
    }

    /**
     * 打开照相机
     */
    /**
     * 从照相机获取图片
     */
    public void getImageFromCamera() {

        imageUri = Uri.fromFile(new
                File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg"));
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //调用相机拍照后照片保存的路径
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
           // Log.d(TAG, "getImageFromCamera: 照相机图片：" + imageUri);
            mActivity.startActivityForResult(getImageByCamera,
                    TAKE_PHOTO);
        } else {
            Toast.makeText(mActivity, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 直接从图库获取
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        mActivity.startActivityForResult(intent, LOCAL_IMAGE_CODE);
    }

}
