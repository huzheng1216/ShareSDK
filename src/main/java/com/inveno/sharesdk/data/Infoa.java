package com.inveno.sharesdk.data;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.inveno.sharesdk.share.ShareMan;
import com.inveno.sharesdk.widget.DefaultSharePopupWindow;

import java.util.Locale;

/**
 * Created by zheng.hu on 2016/6/8.
 */
public class Infoa {

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

    private Activity context;
    private ShareData shareData;
    private DefaultSharePopupWindow sharePopupWindow;

    public static void init(Activity actvity, String appUserId) {
        getActionInfo(actvity);
    }

    public static void init(Activity actvity) {
        init(actvity, null);
    }



    public static final int SHARETYPE_IMAGEANDTEXT = 0;//图文
    public static final int SHARETYPE_IMAGE = 1;//图片
    public static final int SHARETYPE_TEXT = 2;//文字
    public static final int SHARETYPE_MUSIC = 3;//音乐
    public static final int SHARETYPE_VIDEO = 4;//视频
    public static final int SHARETYPE_WEB = 5;//网页
    public static final int SHARETYPE_VOICE = 6;//声音

    private String text = "";//要分享的文字
    private String appName = "";//返回按钮位置要展示的内容（不填则为：返回）
    private String description;//文字描述
    private String title;//标题
    private String imageUrl;//图片链接
    private String musicUrl;//音乐链接
    private String videoUrl;//视频链接
    private String targetUrl;//点击跳转
    private Bitmap bitmap;//图片资源
    private int byteLenth;//图片数组长度
    private byte[] bytes;//图片的字节数组
    private int shareType = -1;
    /**
     * 获取基础配置信息
     *
     * @param act
     */
    private static void getActionInfo(Activity act) {
        KeyInfo.parseXML(act);
    }

    /**
     * 设置要分享的实体
     *
     * @param shareData
     */
    public void setShareData(ShareData shareData) {
        this.shareData = shareData;
    }

    /**
     * 弹窗分享框
     */
    public void show(Activity act) {
        if (sharePopupWindow == null) {
            sharePopupWindow = new DefaultSharePopupWindow(act, null, KeyInfo.enList);
        }
        sharePopupWindow.show();
    }

    /**
     * 释放资源
     *
     * @param context
     */
    public void release(Context context) {
        shareData = null;
    }

    public void onShare(String name) {
        INPlatform platform = null;
        for (INPlatform form : INPlatform.values()) {
            if ((name != null) && (name.equals(form.getName())))
                platform = form;
        }
        ShareMan.doShare(context,platform, shareData);
    }


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
