package com.example.guosaid07142019;

import android.text.method.ReplacementTransformationMethod;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 文本转换
 * @package_name com.example.guosaid07142019
 * @project_name 2019国赛D0714
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
		char[] charOriginal = string.toCharArray();
		return charOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		char[] charReplacement = new char[255];
		for (int i = 0; i < 255; i++) {
			charReplacement[i] = '*';
		}
		return charReplacement;
	}

}
