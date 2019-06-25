package com.ihomefnt.commonlib.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ihomefnt.commonlib.CommonLibInit;

import java.io.*;

/**
 * @author Administrator SharePreference操作类
 */
public class PrefUtil {

    private static PrefUtil instance;

    private SharedPreferences mPreferences;

    private PrefUtil() {
        mPreferences = CommonLibInit.application.getSharedPreferences(
                "PREF_FILE_NAME", 0);
    }

    public void  removeAll(){
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.clear();
        edit.commit();
    }
    public static PrefUtil getInstance() {
        if (instance == null) {
            instance = new PrefUtil();
        }
        return instance;
    }


    /**
     * 获取保存在SharedPrefrences中的boolean类型的值
     *
     * @param key        保存时的key
     * @param defaultVal 默认值
     * @return
     */
    public boolean getPreferencesVal(String key, boolean defaultVal) {
        try {
            return mPreferences.getBoolean(key, defaultVal);
        } catch (Exception e) {
            return defaultVal;
        }

    }

    /**
     * 获取保存在SharedPrefrences中的String类型的值
     *
     * @param key        保存时的key
     * @param defaultVal 默认值
     * @return
     */
    public String getPreferencesVal(String key, String defaultVal) {
        return mPreferences.getString(key, defaultVal);
    }

    /**
     * 获取保存在SharedPrefrences中的int类型的值
     *
     * @param key        保存时的key
     * @param defaultVal 默认值
     * @return
     */
    public int getPreferencesVal(String key, int defaultVal) {
        return mPreferences.getInt(key, defaultVal);
    }

    /**
     * 获取保存在SharedPrefrences中的int类型的值
     *
     * @param key        保存时的key
     * @param defaultVal 默认值
     * @return
     */
    public long getPreferencesVal(String key, long defaultVal) {
        return mPreferences.getLong(key, defaultVal);
    }

    /**
     * 保存boolean 类型的值到SharedPrefrences中
     *
     * @param key   保存时的key
     * @param value 保存时的value
     */
    public void putPreferencesVal(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 保存boolean 类型的值到SharedPrefrences中
     *
     * @param key   保存时的key
     * @param value 保存时的value
     */
    public void putPreferencesVal(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 保存boolean 类型的值到SharedPrefrences中
     *
     * @param key   保存时的key
     * @param value 保存时的value
     */
    public void putPreferencesVal(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 保存boolean 类型的值到SharedPrefrences中
     *
     * @param key   保存时的key
     * @param value 保存时的value
     */
    public void putPreferencesVal(String key, long value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 保存object 类型的值到SharedPrefrences中
     *
     * @param key    保存时的key
     * @param object 保存时的Object对象
     */
    public void putPreferencesObj(String key, Object object) {
        if (null == object) {
            return;
        }
        // // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            // 创建对象输出流，并封装字节流
            oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(object);
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = Base64.encode(baos.toByteArray());
            putPreferencesVal(key, oAuth_Base64);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("--->write obj fail "+e.getMessage());
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.e("--->write obj fail "+e.getMessage());
            }
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtils.e("--->write obj fail "+e.getMessage());
                }
            }
        }
    }

    /**
     * 获取保存在SharedPrefrences中的Object类型的值
     *
     * @param key 保存时的key
     * @return Object 返回的Object对象
     */
    public Object getPreferencesObj(String key) {
        Object obj = null;
        String productBase64 = getPreferencesVal(key, "");
        if (TextUtils.isEmpty(productBase64)) {
            return null;
        }
        // 读取字节
        byte[] base64 = Base64.decode(productBase64);
        // 封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            // 再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                // 读取对象
                obj = bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            bis.close();
            bais.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
