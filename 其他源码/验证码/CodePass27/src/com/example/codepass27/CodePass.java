package com.example.codepass27;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

public class CodePass {

	public static String code = "";
	public static String all_code = "as96dg564ar6h489trkj489tfiy4S89FD4HG894ESR56YH123WER56GQW46";

	public static Bitmap createBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Random random = new Random();
		Paint paint = new Paint();
		paint.setTextSize(20);
		paint.setAntiAlias(true);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(all_code.charAt(random.nextInt(all_code.length())));
		}
		code = buffer.toString();
		int base_left = 20;
		int base_top = 20;
		int random_left = 23;
		int random_top = 0;
		for (int i = 0; i < code.length(); i++) {
			paint.setFakeBoldText(random.nextBoolean());
			paint.setTextSkewX(-random.nextInt(1));
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			random_left += base_left + random.nextInt(10);
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
