package com.example.codepass26;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class CodePass {

	public static String code = "";
	public static String all_code = "87w9q5g6h1tkl5ui1bdj5261bn5DS58G485EW9R7UYT7K8U5SE49R9E984B6W";

	public static Bitmap createBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Random random = new Random();
		Paint paint = new Paint();
		paint.setTextSize(20);
		paint.setAntiAlias(true);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			stringBuffer.append(all_code.charAt(random.nextInt(all_code
					.length())));
		}
		code = stringBuffer.toString();
		int base_left = 20;
		int base_top = 20;
		int random_left = 23;
		int random_top = 0;
		for (int i = 0; i < code.length(); i++) {
			paint.setFakeBoldText(random.nextBoolean());
			paint.setTextSkewX(-random.nextInt(1));
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
