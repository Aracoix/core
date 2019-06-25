package com.ihomefnt.commonlib.utils;

import android.util.Log;
import com.ihomefnt.commonlib.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 这个类是 打印log的
 * Created by Aracoix on 2017/3/15.
 * Email:aracoix@163.com
 */

public class LogUtils {
    public static final String TAG= "aracoix";

    public static void e(String msg){
        if (BuildConfig.DEBUG)
        Log.e(TAG,msg);
    }
    public static String DI(Exception e) {
        return fun(e)+"|";
    }
    public  static String fun(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        if (trace == null)
            return ""; //
        return trace[0].getMethodName();
    }

}

