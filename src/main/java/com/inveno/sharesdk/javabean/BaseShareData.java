package com.inveno.sharesdk.javabean;

import android.content.Context;

import com.inveno.sharesdk.data.ChannelId;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.KeyInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zheng.hu on 2016/10/9.
 */
public class BaseShareData {

    public static final int SHARETYPE_IMAGEANDTEXT = 0;//图文
    public static final int SHARETYPE_IMAGE = 1;//图片
    public static final int SHARETYPE_TEXT = 2;//文字
    public static final int SHARETYPE_MUSIC = 3;//音乐
    public static final int SHARETYPE_VIDEO = 4;//视频
    public static final int SHARETYPE_WEB = 5;//网页
    public static final int SHARETYPE_VOICE = 6;//声音


    private String name;

    private int age;

    private String firstName;

    private String lastName;

    private int id;

    private String pp;

    private String pin;

    private long time;

    private int cd;

    private boolean isT;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getPp() {
        return pp;
    }

    public String getPin() {
        return pin;
    }

    public long getTime() {
        return time;
    }

    public int getCd() {
        return cd;
    }

    public boolean isT() {
        return isT;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public void setT(boolean t) {
        isT = t;
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
        return null;
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
        return 0;
    }
    public String getPlatformName()
    {
        return "";
    }

}
