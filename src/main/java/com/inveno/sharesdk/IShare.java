package com.inveno.sharesdk;

import android.app.Activity;
import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.KeyInfo;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.share.ShareMan;
import com.inveno.sharesdk.widget.DefaultSharePopupWindow;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public class IShare implements DefaultSharePopupWindow.OnShareListener {

    private Activity context;
    private ShareData shareData;
    private DefaultSharePopupWindow sharePopupWindow;

    public static void init(Activity actvity, String appUserId) {
        getActionInfo(actvity);
    }

    public static void init(Activity actvity) {
        init(actvity, null);
    }

    public IShare(Activity context) {
        this.context = context;
    }

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
            sharePopupWindow.setOnShareListener(this);
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

    @Override
    public void onShare(String name) {
        INPlatform platform = null;
        for (INPlatform form : INPlatform.values()) {
            if ((name != null) && (name.equals(form.getName())))
                platform = form;
        }
        ShareMan.doShare(context,platform, shareData);
    }
}
