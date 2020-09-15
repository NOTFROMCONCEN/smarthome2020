package com.example.shengsai2019b0915.tools;

import android.text.method.ReplacementTransformationMethod;

public class TextChanger extends ReplacementTransformationMethod {

	private String string = null;

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
