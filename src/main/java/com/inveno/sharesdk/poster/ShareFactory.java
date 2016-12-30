package com.inveno.sharesdk.poster;

import android.app.Activity;
import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public interface ShareFactory {
    abstract Sharer produceSharer(Activity context, INPlatform platform);
}
