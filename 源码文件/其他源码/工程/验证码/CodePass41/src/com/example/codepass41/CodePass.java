package com.example.codepass41;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class CodePass {

	private static String chat_code = "w8a69ht4j85dht4kj81b56esfb163418589H74T6Y4K51V3AW53QW";
	public static String code = "";

	public static Bitmap createBitmap() {
		code = "";
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Random random = new Random();
		Paint paint = new Paint();
		paint.setTextSize(20);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(chat_code.charAt(random.nextInt(chat_code.length())));
		}
		code = buffer.toString();
		System.out.println(code);
		int base_left = 20;
		int base_top = 20;
		int random_top = 0;
		for (int i = 0; i < code.length(); i++) {
			paint.setAntiAlias(random.nextBoolean());
			paint.setFakeBoldText(random.nextBoolean());
			paint.setTextSkewX(-random.nextInt(1));
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			random_top = base_top + random.nextInt(canvas.getHeight() - 20);
			canvas.drawText(code.charAt(i) + "", base_left, random_top, paint);
			base_left = base_left + base_left;
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