package com.example.codepass45;

import java.util.Random;

import android.R.layout;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class CodePass {
	public static String code = "";
	public static String char_code = "w8795fgh41r8ty441584e1rw84sabv41231W54AHG5697IT9U84JN56RE4N1561SB32XC";

	public static Bitmap createBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Random random = new Random();
		Paint paint = new Paint();
		paint.setTextSize(20);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(char_code.charAt(random.nextInt(char_code.length())));
		}
		code = buffer.toString();
		int base_left = 20;
		int base_top = 20;
		int random_left = 23;
		int random_top = 0;
		for (int i = 0; i < code.length(); i++) {
			paint.setAntiAlias(random.nextBoolean());
			paint.setTextSkewX(-random.nextInt(1));
			paint.setFakeBoldText(random.nextBoolean());
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			random_top = base_top + random.nextInt(10);
			canvas.drawText(code.charAt(i) + "", base_left, random_top, paint);
			base_left += random_left + random.nextInt(10);
		}
		canvas.drawLine(random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()),
				random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()), paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return bitmap;
	}
}
