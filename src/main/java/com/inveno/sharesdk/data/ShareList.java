package com.inveno.sharesdk.data;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

/**
 * 获取各分享频道的icon/name
 * Created by zheng.hu on 2016/4/11.
 */
public class ShareList {
    public static int getLogo(String name, Context context)
    {
        String packName = context.getPackageName();
        Resources res = context.getResources();

        for (INPlatform p : INPlatform.values()) {
            String platName = p.getName();
            if (platName.equals(name))
                return res.getIdentifier("is_" + name.toLowerCase(Locale.US), "drawable", packName);
        }
        return -1;
    }

    public static String getTitle(String name, Context context)
    {
        String packName = context.getPackageName();
        Resources res = context.getResources();

        for (INPlatform p : INPlatform.values()) {
            String platName = p.getName();
            if (platName.equals(name))
                return res.getString(res.getIdentifier("is_" + name.toLowerCase(Locale.US), "string", packName));
        }
        return "";
    }
}
