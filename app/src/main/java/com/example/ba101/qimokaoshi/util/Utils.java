package com.example.ba101.qimokaoshi.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Ba101 on 2019/1/15.
 */

public class Utils {
    //sd卡是否存在
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }


    //获取sd卡路径
    public static String getSDPath() {
        File sdDir = null;
        if (sdCardIsAvailable()) {
            sdDir = Environment.getExternalStorageDirectory(); //获取根目录
        }
        return sdDir.toString();
    }
}
