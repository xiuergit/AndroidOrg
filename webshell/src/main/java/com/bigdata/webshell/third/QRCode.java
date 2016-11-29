package com.bigdata.webshell.third;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by xiuer on 16/9/6.
 */
public class QRCode  {

    /**
     * 生成二维码
     * @param content 传入的字符串
     * @param width  宽度
     * @param height  高度
     * @return
     */
    public static Bitmap createQRImage(String content, int width, int height) {
        try {
            //判断URL合法性
            if (content == null || "".equals(content) || content.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            //显示到一个ImageView上面
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    * 扫描二维码图片的方法
//    * @param path
//    * @return
//            */
    public static String scanningImage(Bitmap bitmap) {

//        if(TextUtils.isEmpty(path)){
//            return null;
//        }
        Hashtable<DecodeHintType, String> hints =
                new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); //设置二维码内容的编码

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        //scanBitmap = BitmapFactory.decodeFile(path, options);


        options.inJustDecodeBounds = false; // 获取新的大小
        int sampleSize = (int) (options.outHeight / (float) 200);
        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        //scanBitmap = BitmapFactory.decodeFile(path, options);




        int width=bitmap.getWidth();
        int height=bitmap.getHeight();

        //Bitmap bitmap2= createBitmap(bitmap,width,height,);

        // 保存所有的像素的数组，图片宽×高
        int[] pixels = new int[width * height];

        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < pixels.length; i++) {
            int clr = pixels[i];
            int red = (clr & 0x00ff0000) >> 16; // 取高两位
            int green = (clr & 0x0000ff00) >> 8; // 取中两位
            int blue = clr & 0x000000ff; // 取低两位
            // Log.d("tag", "r=" + red + ",g=" + green + ",b=" + blue);
        }
        RGBLuminanceSource source = new RGBLuminanceSource(width,height,pixels);




        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeReader reader = new QRCodeReader();
        try {
            return reader.decode(bitmap1, hints).getText();

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
