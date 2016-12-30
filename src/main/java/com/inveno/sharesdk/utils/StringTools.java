package com.inveno.sharesdk.utils;

/**
 * 字符串工具类
 * 
 * @author mingsong.zhang
 * @date 2012-08-07
 */
public class StringTools {

	/** 判断字符是否有内容 为空则返回true **/
	public static boolean isEmpty(String src) {
		if (src == null || src.trim().length() == 0)
			return true;
		else
			return false;
	}

	/** 判断字符是否有内容 不为空返回true **/
	public static boolean isNotEmpty(String src) {
		return !isEmpty(src);
	}

}
