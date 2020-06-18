package com.example.codepass42;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;

public class CodePass {

	public static String code = "";
	public static String chat_code = "wa5g47987h45u464vg5641ae56r48SD4G86EW7TU8Y9O79TUJN456RB1S23WB";

	public static Bitmap createBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		return bitmap;
	}
}
