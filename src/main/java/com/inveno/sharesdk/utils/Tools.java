package com.inveno.sharesdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;

import com.inveno.sharesdk.data.ShareData;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Tools {

    public static final String TAG = "auth";
    private static final String KEY_UID           = "uid";
    private static final String KEY_ACCESS_TOKEN  = "access_token";
    private static final String KEY_EXPIRES_IN    = "expires_in";
    private static final String KEY_REFRESH_TOKEN    = "refresh_token";

	//过滤文字
	public static String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type
				+ System.currentTimeMillis();
	}
	
    /**
     * bitmap-->字节数组
     * @param bitmap
     * @param paramBoolean 是否要释放bitmap
     * @return
     */
	public static byte[] bmpToByteArray(Bitmap bitmap, boolean paramBoolean) {
        Bitmap localBitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);
        int i;
        int j;
        if (bitmap.getHeight() > bitmap.getWidth()) {
            i = bitmap.getWidth();
            j = bitmap.getWidth();
        } else {
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
        while (true) {
            localCanvas.drawBitmap(bitmap, new Rect(0, 0, i, j), new Rect(0, 0,80, 80), null);
            if (paramBoolean)
                bitmap.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                e.printStackTrace();
            }
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
    }

    public static void openSystemShare(Activity activity, ShareData shareData)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        if ((shareData.getImageUrl() == null) || (shareData.getImageUrl().equals(""))) {
            intent.setType("text/plain");
        } else {
            File f = new File(shareData.getImageUrl());
            if ((f != null) && (f.exists()) && (f.isFile())) {
                intent.setType("image/*");
                Uri u = Uri.fromFile(f);
                intent.putExtra("android.intent.extra.STREAM", u);
            }
        }
        intent.putExtra("android.intent.extra.SUBJECT", shareData.getTitle());
        if (shareData.getShareType() == 0) {
            if (shareData.getTargetUrl() != null) {
                intent.putExtra("android.intent.extra.TEXT", shareData.getText() + shareData.getTargetUrl());
                intent.putExtra("sms_body", shareData.getText() + shareData.getTargetUrl());
            } else {
                intent.putExtra("android.intent.extra.TEXT", shareData.getText());
                intent.putExtra("sms_body", shareData.getText());
            }
        }
        intent.setFlags(268435456);
        activity.startActivity(Intent.createChooser(intent, "分享"));
    }


    /**
     * 设置sina微博token信息
     */
    public static void setInformain(Context context, Oauth2AccessToken token) {
        if(context == null || null == token) {
            return;
        }
        SharedPreferences settings = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_UID, token.getUid());
        editor.putString(KEY_ACCESS_TOKEN, token.getToken());
        editor.putString(KEY_REFRESH_TOKEN, token.getRefreshToken());
        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
        editor.commit();
    }

    /**
     * 取sina微博token信息
     */
    public static Oauth2AccessToken getInformain(Context context) {
        if(context == null) {
            return null;
        }
        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        token.setUid(pref.getString(KEY_UID, ""));
        token.setToken(pref.getString(KEY_ACCESS_TOKEN, ""));
        token.setRefreshToken(pref.getString(KEY_REFRESH_TOKEN, ""));
        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));
        return token;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     *
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 将图片转字节数组
     * @param bitmap
     * @return
     */
    public static byte[] getBytes(Bitmap bitmap){
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
    }

    /**
     * 字节数组转图片
     * @param data
     * @return
     */
    public static Bitmap getBitmap(byte[] data){
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }
}
