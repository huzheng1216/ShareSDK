package com.inveno.sharesdk.data;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 分享类型
 * Created by zheng.hu on 2016/4/8.
 */
public enum INPlatform {

    PLATFORM_WECHAT,

    PLATFORM_WECHATMOMENTS,
    PLATFORM_WECHATFAVORITE,
    PLATFORM_QQ,
    PLATFORM_QZONE,
    PLATFORM_SINAWEIBO,
    PLATFORM_TENCENTWEIBO,
    PLATFORM_YIXIN,
    PLATFORM_YIXINFRIENDS,
    PLATFORM_KAIXIN,
    PLATFORM_RENREN,
    PLATFORM_SHORTMESSAGE,
    PLATFORM_EMAIL,
    PLATFORM_COPYLINK,
    PLATFORM_SCREENCAP,
    PLATFORM_MORE;

    public static final int PLATFORMTYPE_SOCIAL = 0;
    public static final int PLATFORMTYPE_SYSTEM = 1;
    public static final int PLATFORMTYPE_UTIL = 2;
    private static Map<INPlatform, String> platMap;

    static { platMap = new HashMap(); }

    public static String getPlatformName(INPlatform platform)
    {
        if (platMap.containsKey(platform))
            return (String)platMap.get(platform);
        return platform.toString().toLowerCase(java.util.Locale.US).split("platform_")[1];
    }

    public static INPlatform getPlatformByName(String platform)
    {
        for (INPlatform p : values()) {
            if (p.getName().equalsIgnoreCase(platform))
                return p;
        }
        return null;
    }

    public String getName() {
        return getPlatformName(this);
    }

    public String getTitleName(Context context) {
        return context.getString(context.getResources().getIdentifier("yt_" + getName(), "string", context.getPackageName()));
    }

    public int getChannleId()
    {
        String platName = toString().toLowerCase(java.util.Locale.US)
                .split("platform_")[1];
        for (ChannelId channelId : ChannelId.values()) {
            String name = channelId.toString();
            int index = name.lastIndexOf("_");
            if (name.substring(0, index).equalsIgnoreCase(platName)) {
                return Integer.parseInt(name.substring(index + 1));
            }
        }
        return -1;
    }

    public static int getPlatformType(INPlatform platform) {
        if ((platform == PLATFORM_COPYLINK) ||
                (platform == PLATFORM_SCREENCAP))
            return 2;
        if ((platform == PLATFORM_SHORTMESSAGE) ||
                (platform == PLATFORM_EMAIL) ||
                (platform == PLATFORM_MORE)) {
            return 1;
        }
        return 0;
    }

    public String getAppId()
    {
        return getKeyInfor(getName() + "AppId");
    }

    public String getAppKey() {
        return getKeyInfor(getName() + "AppKey");
    }

    public String getAppSecret() {
        return getKeyInfor(getName() + "AppSecret");
    }

    public String getEnable() {
        return getKeyInfor(getName() + "Enable");
    }

    public String getAppRedirectUrl() {
        return getKeyInfor(getName() + "RedirectUrl");
    }

    private static String getKeyInfor(String name) {
        if (KeyInfo.KeyInforMap.containsKey(name)) {
            String value = (String) KeyInfo.KeyInforMap.get(name);
            if (value != null)
                return value;
        }
        return "";
    }
}
