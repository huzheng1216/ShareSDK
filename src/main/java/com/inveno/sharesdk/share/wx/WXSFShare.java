package com.inveno.sharesdk.share.wx;

import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * 微信收藏
 * Created by zheng.hu on 2016/4/8.
 */
public class WXSFShare extends WXShare{

    public WXSFShare(Context context, INPlatform platform) {
        super(context, platform);
    }

    @Override
    public void shareText(ShareData sd) {
        LogTools.showLog("暂时不支持纯文字收藏！");
    }

    @Override
    public void send() {
        if (req == null) return;
        req.scene = SendMessageToWX.Req.WXSceneFavorite;
        api.sendReq(req);
        LogTools.showLog("分享到：微信收藏");
    }
}
