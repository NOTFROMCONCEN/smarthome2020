package com.example.codepass12;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;

public class CodePass {
	// 最终输出的String
	public static String code_String = "";
	// 随机库
	private static String code_allString = "qwq87e944hsmjn894789to789yt8awseg8458QEW4R847EYFGJ84FGK8M74";

	/**
	 * 生成一个的Bitmap位图
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// 创建一个bitmap位图
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// 创建画布
		Canvas canvas = new Canvas(bitmap);
		// 创建随机数
		Random random = new Random();
		// 创建画笔
		Paint paint = new Paint();
		// 设置抗锯齿
		paint.setAntiAlias(true);
		// 设置文本大小
		paint.setTextSize(20);
		// 新建StringBuffer
		StringBuffer buffer = new StringBuffer();
		// 开始循环添加随机数
		for (int i = 0; i < 4; i++) {
			// 添加新生成的随机数
			buffer.append(code_allString.charAt(random.nextInt(code_allString
					.length())));
		}
		// 给code_String赋值
		code_String = buffer.toString();
		// 设置默认左边距
		int base_left = 20;
		// 设置默认顶边距
		int base_top = 20;
		// 设置随机左边距
		int random_left = 23;
		// 设置随机顶边距
		int random_top = 0;
		// 开始循环绘制随机验证码
		for (int i = 0; i < code_String.length(); i++) {
			// 设置随机颜色
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			// 设置是否加粗
			paint.setFakeBoldText(random.nextBoolean());
			// 设置倾斜角度
			paint.setTextSkewX(-random.nextInt(1));
			// 设置随机顶边距
			random_top = base_top + random.nextInt(10);
			// 绘制文本
			canvas.drawText(code_String.charAt(i) + "", base_left, random_top,
					paint);
			// 设置下一个绘制位置的左边距
			base_left += random_left;
		}
		// 随机绘制一条线
		canvas.drawLine(random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()),
				random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()), paint);
		// 保存全部画布
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// 重置画布
		canvas.restore();
		// 返回一个bitmap位图
		return bitmap;
	}

}
