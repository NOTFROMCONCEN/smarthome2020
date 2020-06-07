package com.example.codepass9;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 生成随机验证码
 * @package_name com.example.codepass9
 * @project_name CodePass9
 * @file_name PassCode.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class PassCode {
	// 最终输出的随机验证码
	public static String codeString = "";

	/**
	 * 最终输出的Bitmap位图
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// Bitmap位图
		final Bitmap bitmap = Bitmap.createBitmap(130, 50, Config.ARGB_8888);
		// 画布
		final Canvas canvas = new Canvas(bitmap);
		// 画笔
		final Paint paint = new Paint();
		// 随机数
		final Random random = new Random();
		// 开启绘制线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < 2; i++) {
					char code_char = (char) (Math.random() * 26 + 'a');
					buffer.append(String.valueOf(code_char) + random.nextInt(9));
				}
				codeString = buffer.toString();
				paint.setTextSize(20);
				paint.setAntiAlias(true);
				int base_left = 20;
				int base_top = 20;
				int random_left = 23;
				int random_top = 0;
				for (int i = 0; i < codeString.length(); i++) {
					paint.setColor(Color.rgb(random.nextInt(256),
							random.nextInt(256), random.nextInt(256)));
					paint.setFakeBoldText(random.nextBoolean());
					paint.setTextSkewX(-random.nextInt(1));
					random_top = base_top + random.nextInt(10);
					canvas.drawText(codeString.charAt(i) + "", base_left,
							random_top, paint);
					base_left += random_left;
				}
				canvas.drawLine(random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()),
						random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()), paint);
				canvas.save(Canvas.ALL_SAVE_FLAG);
				canvas.restore();
			}
		}).start();
		return bitmap;
	}
}
