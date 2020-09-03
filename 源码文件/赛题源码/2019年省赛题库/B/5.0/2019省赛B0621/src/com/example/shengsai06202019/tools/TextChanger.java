package com.example.shengsai06202019.tools;

import android.text.method.ReplacementTransformationMethod;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 更改文本为 *
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019省赛B0620
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
		char[] charori = string.toCharArray();
		return charori;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		char[] charepla = new char[255];
		for (int i = 0; i < 255; i++) {
			charepla[i] = '*';
		}
		return charepla;
	}

}
