package com.cbdry.app;

/**
 * 单列模式测试
 */
public class SingLeton {

    private volatile static SingLeton singLeton;

    private SingLeton(){}//私有化构造方法

    public synchronized static SingLeton getSingLeton() {
        if(singLeton==null){
            synchronized (SingLeton.class){
                if (singLeton==null){
                    singLeton=new SingLeton();
                }
            }
        }
        return  singLeton;
    }
}
