package com.example.shengsai2018b0912.tools;

import android.text.method.ReplacementTransformationMethod;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 文本框输入字符文本转换
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018省赛B0912
 * @file_name TextChanger.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class TextChanger extends ReplacementTransformationMethod {

	String string = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		for (char i = 0; i < 256; i++) {
			string += String.valueOf(i);
		}
		char[] getOriginal = string.toCharArray();
		return getOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		char[] getReplacement = new char[255];
		for (int i = 0; i < 255; i++) {
			getReplacement[i] = '*';
		}
		return getReplacement;
	}

}
