package com.inveno.sharesdk.share.kaixin;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;

/**
 * Created by zheng.hu on 2016/7/14.
 */
public class KaiXinShareFactory  implements ShareFactory {
    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new KaiXinShare(context, platform);
    }
}
