package com.ihomefnt.commonlib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public MD5Util() {
    }

    public static String getMD5(String var0) throws NoSuchAlgorithmException {
        MessageDigest var1 = null;

        try {
            var1 = MessageDigest.getInstance("MD5");
        } catch (Exception var6) {
            System.out.println(var6.toString());
            var6.printStackTrace();
            return "";
        }

        byte[] var2 = var1.digest(var0.getBytes());
        StringBuffer var3 = new StringBuffer();

        for(int var4 = 0; var4 < var2.length; ++var4) {
            int var5 = var2[var4] & 255;
            if (var5 < 16) {
                var3.append("0");
            }

            var3.append(Integer.toHexString(var5));
        }

        return var3.toString();
    }
}
