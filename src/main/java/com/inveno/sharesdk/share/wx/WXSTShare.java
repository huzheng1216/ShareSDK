package com.inveno.sharesdk.share.wx;

import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * 微信朋友圈
 * Created by zheng.hu on 2016/4/8.
 */
public class WXSTShare extends WXShare{

    public WXSTShare(Context context, INPlatform platform) {
        super(context, platform);
    }

    @Override
    public void send() {
        if (req == null) return;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
        LogTools.showLog("分享到：微信朋友圈");
    }
}
