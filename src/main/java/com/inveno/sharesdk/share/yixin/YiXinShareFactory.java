package com.inveno.sharesdk.share.yixin;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;

/**
 * Created by zheng.hu on 2016/8/16.
 */
public class YiXinShareFactory implements ShareFactory {

    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new YiXinShare(context, platform);
    }
}
