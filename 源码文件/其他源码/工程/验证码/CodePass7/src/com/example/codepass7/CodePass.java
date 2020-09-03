package com.example.codepass7;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class CodePass {

	private static Random random = new Random();
	private static String random_code = "789456132qewasdfcxzvfghryuioplkjmQWEDASZXCVFGRUIOPLJKM";
	public static String code = "";

	private static String mCode() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			builder.append(random_code.charAt(random.nextInt(random_code
					.length())));
		}
		return builder.toString();
	}

	public static Bitmap createBitmap() {
		code = mCode();
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setTextSize(20);
		paint.setAntiAlias(true);
		int base_padding_left = 20;
		int base_padding_top = 20;
		int random_padding_top = 0;
		int random_padding_left = 23;
		for (int i = 0; i < code.length(); i++) {
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			paint.setFakeBoldText(random.nextBoolean());
			float[] ske = { (float) 0.5, 0, (float) 0.5, 0 };
			paint.setTextSkewX(ske[random.nextInt(ske.length)]);
			random_padding_top = base_padding_top + random.nextInt(10);
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					random_padding_top, paint);
			base_padding_left += random_padding_left;
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