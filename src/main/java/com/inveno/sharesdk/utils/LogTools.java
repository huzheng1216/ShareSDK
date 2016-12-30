package com.inveno.sharesdk.utils;

import android.util.Log;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public class LogTools {

    public static final String tag = "zheng.hu";
    public static final boolean  showLog = true;

    public static void showLog(String msg) {
        if (showLog)
            if (msg == null)
                Log.i(tag, "null");
            else if (msg == "")
                Log.i(tag, "not null");
            else
                Log.i(tag, msg);
    }

}
