package com.inveno.sharesdk.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * 手机硬件参数数据类
 * Created by zheng.hu on 2016/7/5.
 */
public class DeviceConfig {

    /** 手机屏幕宽高（分辨率） */
    private static int w;
    private static int h;
    public static String imei;
    public static String mac;
    public static String net = "WIFI";
    public static String operator;
    public static String osVersion;

    public static String country;

    public static int StatusBarHeight;

    /**
     * 得到屏幕尺寸数据存入DeviceConfig
     */
    public static void initScreenSize(Context context) {
        if (w == 0 || h == 0) {
            reinstallScreenSize(context);
        }
    }

    /**
     * 重新得到屏幕尺寸数据存入DeviceConfig
     */
    public static void reinstallScreenSize(Context context) {
        DisplayMetrics size = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(size);
        w = size.widthPixels;
        h = size.heightPixels;
    }

    public static int getDeviceWidth(){
        return w;
    }


    public static int getDeviceHeight(){
        return h;
    }


    public static String getCountry() {
        return country;
    }

    /**
     * 网络发生变化，重新获取网络状态
     *
     * @Title: resetNetStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return void 返回类型
     * @throws
     */
//    public static void resetNetStatus(Context context) {
//        int netInt = NetWorkUtil.getNetWorkType(context);
//        switch (netInt) {
//            case NetWorkUtil.NETWORKTYPE_2G:
//                net = "2G";
//                break;
//            case NetWorkUtil.NETWORKTYPE_3G:
//                net = "3G";
//                break;
//            case NetWorkUtil.NETWORKTYPE_WIFI:
//                net = "WIFI";
//                break;
//        }
//        LogTools.showLogB("网络发生变化，重新获取：" + net);
//    }

    /**
     *
     * @Title: initDeviceData
     * @Description: 初始化设备基础数据
     * @param @param context 设定文件
     * @return void 返回类型
     * @throws
     */
//    public static void initDeviceData(Context context) {
//        if (StringTools.isEmpty(imei))
//            imei = TelephonyManagerTools.getImei(context);
//        if (StringTools.isEmpty(mac))
//            mac = TelephonyManagerTools.getMacAddress(context);
//        if (StringTools.isEmpty(operator))
//            operator = TelephonyManagerTools.getProvidersName(context);
//        if (StringTools.isEmpty(net)) {
//            int netInt = NetWorkUtil.getNetWorkType(context);
//            switch (netInt) {
//                case NetWorkUtil.NETWORKTYPE_2G:
//                    net = "2G";
//                    break;
//                case NetWorkUtil.NETWORKTYPE_3G:
//                    net = "3G";
//                    break;
//                case NetWorkUtil.NETWORKTYPE_WIFI:
//                    net = "WIFI";
//                    break;
//            }
//            LogTools.showLogB("获取网络：" + net);
//        }
//        if(StatusBarHeight==0)
//            StatusBarHeight = getStatusBarHeight(context);
//
//        if(StringTools.isEmpty(country)){
//            country = getOsLocaleLanguage(context);
//        }
//    }

    // 获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    /**
     * 获取当前系统的语言环境
     * @Title: getOsLocale
     * @return String    返回类型
     * @return
     * @throws
     */
    public static String getOsLocaleLanguage(Context context){
        String lang = "";
        Locale l = context.getResources().getConfiguration().locale;
        if(l != null) {
            lang = l.getLanguage() + "_" + l.getCountry();
        }

        /**
         * modify by&date: mingsong.zhang&2015-11-10
         * causes: 切换新加波（简体）时，服务器返回的分类还是英文的
         * solution：将新加波（简体）语言当作中文（简体）来处理
         * tips:
         */
        if("zh_SG".equals(lang)){
            lang = "zh_CN";
        }

        return lang;
    }

    /**
     * 获取当前系统的语言环境
     * @Title: getOsLocale
     * @return String    返回类型
     * @return
     * @throws
     */
    public static String getOsLanguage(Context context){
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    /**
     * 获取当前系统的语言环境
     * @Title: getOsLocale
     * @return String    返回类型
     * @return
     * @throws
     */
    public static String getOsCountry(Context context){
        return context.getResources().getConfiguration().locale.getCountry();
    }

    public static void resetLanguage(Context context) {
        String lang = getOsLocaleLanguage(context);
        if(StringTools.isNotEmpty(lang)){
            country = lang;
        }
    }
}
