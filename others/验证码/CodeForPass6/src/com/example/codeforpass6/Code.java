package com.example.codeforpass6;

import java.util.Random;

import javax.crypto.spec.OAEPParameterSpec;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 随机验证码生成
 * @package_name com.example.codeforpass6
 * @project_name CodeForPass6
 * @file_name Code.java
 */
public class Code {
	private static int HEIGHT = 40;
	private static int WIDTH = 140;
	private static String strings_CODE = "1234567890qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
	private static final Random random = new Random();
	private static int base_padding_left;
	private static final int base_padding_top = 20;
	private static final int random_padding_top = 10;
	private static final int random_padding_left = 23;
	public static String code = "";

	private static int mColor() {
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		return Color.rgb(red, green, blue);
	}

	private static String mCode() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			builder.append(strings_CODE.charAt(random.nextInt(strings_CODE
					.length())));
		}
		return builder.toString();
	}

	private static void mDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		int color = mColor();
		paint.setColor(color);
		int startX = random.nextInt(WIDTH);
		int startY = random.nextInt(HEIGHT);
		int stopX = random.nextInt(WIDTH);
		int stopY = random.nextInt(HEIGHT);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}

	public static Bitmap createBitmap() {
		code = mCode();
		Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setTextSize(25);
		base_padding_left = 20;
		for (int i = 0; i < code.length(); i++) {
			int color = mColor();
			paint.setColor(color);
			paint.setFakeBoldText(false);
			float skewX = random.nextInt(11) / 10;
			skewX = random.nextBoolean() ? skewX : (-skewX);
			paint.setTextSkewX(skewX);
			int padding_top = base_padding_top
					+ random.nextInt(random_padding_top);
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					padding_top, paint);
			base_padding_left += random_padding_left;
		}
		mDraw(canvas, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return bitmap;
	}
}