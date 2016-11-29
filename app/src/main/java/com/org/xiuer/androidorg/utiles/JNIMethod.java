package com.org.xiuer.androidorg.utiles;

/**
 * Created by xiuer on 16/11/24.
 */

public class JNIMethod {

   private  final  static  String TAG="JNIMethod";
    static {

       // System.loadLibrary("hello-jni");
    }

    public  native  final String stringFromJNI();


    /*
     * 调用native方法
     */
    public   void  JNITest(){

       // Log.i(TAG, "JNITest: "+stringFromJNI());

    }
}
