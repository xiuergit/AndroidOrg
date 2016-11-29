package com.org.xiuer.androidorg;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigdata.webshell.third.QRCode;

import org.xutils.image.ImageDecoder;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ContentView(R.layout.activity_web_shell_test)
    public class WebShellTestActivity extends BaseActivity {
       private  String TAG="xiuer";

        Bitmap scanBitmap;
        @ViewInject(R.id.imageViewCode)
        ImageView mCode;
        @ViewInject(R.id.editCode)
        EditText mEditCode;
        @ViewInject(R.id.imageViewCompress)
        ImageView mCompress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          x.view().inject(this);

             mCompress.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //解吗
                     Bitmap  bitmap =((BitmapDrawable)(((ImageView)view).getDrawable())).getBitmap();
                      String result=QRCode.scanningImage(bitmap);
                      new AlertDialog.Builder(WebShellTestActivity.this)
                             .setMessage(result)
                             .show();
                 }
             });
    }
    /***
     * 生成二维码
     * @param view
     */
        public  void  generativeCode(View view){
              String content=mEditCode.getText().toString();
              Bitmap bitmap= QRCode.createQRImage(content,100,100);
              mCode.setImageBitmap(bitmap);
        }

    /***
     * 图片压缩
     * @param v
     */
        public  void compressAnalysisCode(View v){

            Bitmap bitmap=
                    BitmapFactory.decodeResource(getResources(),R.drawable.code);

            ByteArrayOutputStream os=new ByteArrayOutputStream(512);

            Log.i(TAG, "compress: before"+bitmap.getConfig()+"width:"+
                    bitmap.getWidth() +"密度："+bitmap.getDensity()+
                    "字节大小"+bitmap.getByteCount());

            try {
                ImageDecoder.compress(bitmap, Bitmap.CompressFormat.JPEG,1, os);
                byte array[]= os.toByteArray();
                Bitmap bitmap1=BitmapFactory.decodeByteArray(array,0,array.length);

                Log.i(TAG, "compress: after"+bitmap.getConfig()+"width:"+
                        bitmap.getWidth() +"密度："+bitmap.getDensity()+
                        "字节大小"+bitmap.getByteCount());
                mCode.setImageBitmap(bitmap1);

                String result=QRCode.scanningImage(bitmap1);

                Log.i(TAG, "onCreate: ");
                showLog(result+"");
                Log.i(TAG, "onCreate: "+result);

                if(result!=null){
                    String resultCode=result;
                    mEditCode.setHint(resultCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public  void  read(){
            BitmapDrawable bitmap= (BitmapDrawable) mCode.getDrawable();
            Bitmap bitmap1=bitmap.getBitmap();
            String result=QRCode.scanningImage(bitmap1);
            if(result!=null){
                String resultCode=result;
                mEditCode.setText(resultCode);
            }
        }

    /***
     * 解析二维码
     * @param view
     */
        public  void analysisCode(View view){

          read();

        }






    }