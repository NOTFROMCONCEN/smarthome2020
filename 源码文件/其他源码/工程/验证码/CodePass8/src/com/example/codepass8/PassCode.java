package com.example.codepass8;

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
 * @package_name com.example.codepass8
 * @project_name CodePass8
 * @file_name PassCode.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class PassCode {
	// 最终输出的随机验证码
	public static String code;

	/**
	 * 生成Bitmap位图
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// 创建位图
		final Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// 开启绘制线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Random random = new Random();
				// 生成随机数
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < 2; i++) {
					char code_char = (char) (Math.random() * 26 + 'a');// 生成26个小写字母中其中一个
					// 将生成的字母和随机生成的数字添加到StringBuffer内
					buffer.append(String.valueOf(code_char) + random.nextInt(9));
				}
				// 设置随机数
				code = buffer.toString();
				// 创建画布
				Canvas canvas = new Canvas(bitmap);
				// 创建画笔
				Paint paint = new Paint();
				// 设置画笔
				paint.setTextSize(20);// 文本大小
				paint.setAntiAlias(true);// 抗锯齿
				// 设置边距
				int base_left = 20;
				int base_top = 20;
				int random_left = 23;
				int random_top = 0;
				// 开始循环绘制文本
				for (int i = 0; i < code.length(); i++) {
					// 随机颜色
					paint.setColor(Color.rgb(random.nextInt(256),
							random.nextInt(256), random.nextInt(256)));
					// 是否加粗
					paint.setFakeBoldText(random.nextBoolean());
					// 是否倾斜
					paint.setTextSkewX(-random.nextInt(1));
					// 计算下一个绘制的边距
					random_top = base_top + random.nextInt(10);
					// 绘制文本
					canvas.drawText(code.charAt(i) + "", base_left, random_top,
							paint);
					// 计算边距
					base_left += random_left;
				}
				// 随机绘制一条线
				canvas.drawLine(random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()),
						random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()), paint);
				// 保存
				canvas.save(Canvas.ALL_SAVE_FLAG);
				// 重置
				canvas.restore();

			}
		}).start();
		return bitmap;
	}
}