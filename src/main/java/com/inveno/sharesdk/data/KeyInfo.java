package com.inveno.sharesdk.data;

import android.content.Context;
import android.text.TextUtils;

import com.inveno.sharesdk.utils.LogTools;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析配置文件
 * Created by zheng.hu on 2016/4/8.
 */
public class KeyInfo
{
    public static String inveno_AppKey;
    public static String inveno_AppSecret;
    public static Map<String, String> KeyInforMap = new HashMap();

    public static ArrayList<String> enList = new ArrayList();

    public static void parseXML(Context context)
    {
        try
        {
            enList.clear();
            InputStream in = context.getResources().getAssets().open("inveno_sdk.xml");
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != 1) {
                switch (parser.getEventType()) {
                    case 2:
                        String tag = parser.getName();
                        LogTools.showLog(tag);
                        if ("INVENO".equals(tag))
                        {
                            inveno_AppKey = parser.getAttributeValue(null, "AppKey");
                            inveno_AppSecret = parser.getAttributeValue(null, "AppSecret");
                        }
                        for (INPlatform p : INPlatform.values()) {
                            String name = p.getName();
                            if (name.equalsIgnoreCase(tag)) {
                                String AppId = parser.getAttributeValue(null, "AppId");
                                String AppKey = parser.getAttributeValue(null, "AppKey");
                                String AppSecret = parser.getAttributeValue(null, "AppSecret");
                                String RedirectUrl = parser.getAttributeValue(null, "RedirectUrl");
                                String Enable = parser.getAttributeValue(null, "Enable");
                                if (!TextUtils.isEmpty(AppId))
                                    KeyInforMap.put(name + "AppId", AppId);
                                if (!TextUtils.isEmpty(AppKey))
                                    KeyInforMap.put(name + "AppKey", AppKey);
                                if (!TextUtils.isEmpty(AppSecret))
                                    KeyInforMap.put(name + "AppSecret", AppSecret);
                                if (!TextUtils.isEmpty(RedirectUrl))
                                    KeyInforMap.put(name + "RedirectUrl", RedirectUrl);
                                if ((!TextUtils.isEmpty(Enable)) && ("true".equalsIgnoreCase(Enable))) {
                                    KeyInforMap.put(name + "Enable", Enable);
                                    enList.add(name);
                                }
                            }
                        }
                        break;
                    case 3:
                        break;
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            LogTools.showLog("iveno_sdk.xml error");
            e.printStackTrace();
        }
    }

    public static String getKeyValue(Context context, INPlatform paltform, String key)
    {
        String value = null;
        try
        {
            InputStream in = context.getResources().getAssets().open("inveno_sdk.xml");
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != 1) {
                switch (parser.getEventType()) {
                    case 2:
                        String tag = parser.getName();
                        if (!paltform.getName().equalsIgnoreCase(tag)) break;
                        value = parser.getAttributeValue(null, key);

                        break;
                    case 3:
                        break;
                }

                eventType = parser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return value;
    }
}
