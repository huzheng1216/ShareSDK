package com.inveno.sharesdk.share.wanglai;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;

/**
 * Created by zheng.hu on 2016/8/4.
 */
public class WanglaiShareFactory implements ShareFactory {

    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new WanglaiShare(context, platform);
    }
}
