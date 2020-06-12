package com.example.codeforpass5;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 随机验证码
 * @package_name com.example.codeforpass5
 * @project_name CodeForPass5
 * @file_name CodeView.java
 */
public class CodeView {
	private static int HEIGHT = 40;// 高
	private static int WIDTH = 140;// 宽
	private static String[] CODE = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A",
			"S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B",
			"N", "M", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a",
			"s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b",
			"n", "m" };// 随机库
	private static final Random random = new Random();// 随机数
	private static int base_padding_left;// 左边距
	private static final int base_padding_top = 20;// 顶边距
	private static final int random_padding_top = 10;// 随机顶边距
	private static final int random_padding_left = 23;
	public static String code = "";// 最后输出的随机码

	private static int mColor() {
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		return Color.rgb(red, green, blue);
	}

	private static String mCode() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			buffer.append(CODE[random.nextInt(CODE.length)]);
		}
		return buffer.toString();
	}

	private static void mDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		int color = mColor();
		paint.setColor(color);
		int startX = random.nextInt(WIDTH);
		int startY = random.nextInt(HEIGHT);
		int endX = random.nextInt(WIDTH);
		int endY = random.nextInt(HEIGHT);
		canvas.drawLine(startX, startY, endX, endY, paint);
	}

	public static Bitmap createBitmap() {
		code = mCode();
		System.out.println(code);
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
