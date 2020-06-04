package com.example.textdemo0603;

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
 * @package_name com.example.textdemo0603
 * @project_name 0603TextDemo
 * @file_name PassCode.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class PassCode {
	private static Random random = new Random();// 随机生成
	// 随机数库
	private static String random_code = "123xzc456789q78qr9wr4we41gioitydQEWRQQWERTGFDS849BSD4YUO8P5463DFHBVC";
	public static String code = "";// 最终输出的随机数

	/**
	 * 生成四位数验证码
	 * 
	 * @return
	 */
	private static String mCode() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < 4; i++) {// 随机四次
			buffer.append(random_code.charAt(random.nextInt(random_code
					.length())));// 把每一次生成的随机数添加进buffer
		}
		return buffer.toString();// 转换格式
	}

	public static Bitmap createBitmap() {
		// 创建一个四位数随机验证码
		code = mCode();
		// 创建一个bitmap
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// 画布设置
		Canvas canvas = new Canvas(bitmap);
		// 画壁设置
		Paint paint = new Paint();
		paint.setTextSize(20);// 设置文本大小
		paint.setAntiAlias(true);// 设置抗锯齿
		// 设定默认边距
		int base_padding_left = 20;// 默认左边距
		int base_padding_top = 20;// 默认顶边距
		int random_padding_left = 23;// 随机左边距（绘制第二个随机数用）
		int random_padding_top = 0;// 随机顶边距（绘制第二个随机数用）
		for (int i = 0; i < code.length(); i++) {
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));// 设置当前 i 的画笔颜色
			paint.setFakeBoldText(random.nextBoolean());// 设置文本是否加粗
			// 设置是否倾斜、倾斜角度
			float[] ske = { (float) 0.5, (float) 0.5, 0, 0 };
			paint.setTextSkewX(ske[random.nextInt(ske.length)]);// 设置画笔倾斜角度
			random_padding_top = base_padding_top + random.nextInt(10);
			// 绘制这个随机数
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					random_padding_top, paint);
			// 设置下一个画笔绘制位置
			base_padding_left += random_padding_left;
		}
		// 绘制一条随机的线
		canvas.drawLine(random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()),
				random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()), paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);// 保存全部
		canvas.restore();// 重置画布
		return bitmap;// 返回bitmap
	}
}