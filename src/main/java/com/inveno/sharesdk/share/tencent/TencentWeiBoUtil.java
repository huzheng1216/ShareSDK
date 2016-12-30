package com.inveno.sharesdk.share.tencent;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zheng.hu on 2016/4/15.
 */
public class TencentWeiBoUtil {

    public static boolean isTencentWbAuthExpired(Context context) {
        boolean expired = true;
        SharedPreferences preference = context.getSharedPreferences("ANDROID_SDK", 0);
        String authorizeTimeStr = preference.getString("AUTHORIZETIME", null);
        String expiresTime = preference.getString("EXPIRES_IN", null);
        long currentTime = System.currentTimeMillis() / 1000L;
        if ((expiresTime != null) && (expiresTime != "") && (authorizeTimeStr != null) && (authorizeTimeStr != "") &&
                (Long.valueOf(authorizeTimeStr).longValue() + Long.valueOf(expiresTime).longValue() > currentTime)) {
            expired = false;
        }

        return expired;
    }
}
