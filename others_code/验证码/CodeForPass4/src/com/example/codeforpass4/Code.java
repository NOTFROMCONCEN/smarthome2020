package com.example.codeforpass4;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 绘制随机验证码
 * @package_name com.example.codeforpass3
 * @project_name CodeForPass3
 * @file_name CodeView.java
 */
public class Code {
	private static int HEIGHT = 50;// 位图高
	private static int WIDTH = 140;// 位图宽
	private static final char[] CODE_CHARS = { '1', '2', '3', '4', '5', '1',
			'6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
			'x', 'z', 'l', 'k', 'j', 'h', 'g', 'f', 'd', 's', 'a', 'p', 'o',
			'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I',
			'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
			'M', 'N', 'B', 'V', 'C' };// 随机库
	private static final int xCode_Line_Number = 1;// 线条个数
	private static final int xCode_Random_NUMBER = 4;// 随机数个数
	private static final Random random = new Random();// 随机数
	private static final int FontSize = 25;// 文字字体大小
	private static int base_padding_left;// 左边距
	private static final int base_padding_top = 30;// 顶边距
	private static final int random_padding_left = 23;// 随机左边距
	private static final int random_padding_top = 10;// 随机顶边距
	public static String code = "";// 最终输出的随机码

	/**
	 * 需要优先完成的项目
	 * 
	 * 1.createRandomText 生成随机数
	 * 
	 * 2.RandomColor 生成随机颜色
	 * 
	 * 3.mDrawLine 绘制一个随机线条
	 */

	// 方法：mDrawLine 描述：绘制一个随机线条
	private static void mDrawLine(Canvas canvas, Paint paint) {
		int color = RandomColor();
		paint.setColor(color);
		int startX = random.nextInt(WIDTH);// X = 宽
		int startY = random.nextInt(HEIGHT);// Y = 高
		int endX = random.nextInt(WIDTH);
		int endY = random.nextInt(HEIGHT);
		canvas.drawLine(startX, startY, endX, endY, paint);
	}

	// 方法：RandomColor 描述：生成一组颜色（红绿蓝三色色值范围均在0-256）
	private static int RandomColor() {
		int red = random.nextInt(256);// 红
		int green = random.nextInt(256);// 绿
		int blue = random.nextInt(256);// 蓝
		return Color.rgb(red, green, blue);
	}

	// 方法：createRandomText 描述：生成一组随机数（数量根据设定“xCode_Random_NUMBER”）
	private static String createRandomText() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < xCode_Random_NUMBER; i++) {
			builder.append(CODE_CHARS[random.nextInt(CODE_CHARS.length)]);
		}
		return builder.toString();
	}

	/**
	 * 方法：createRandomBitMap
	 * 
	 * 描述：随机生成验证码视图
	 * 
	 * @return
	 */

	public static Bitmap createRandomBitMap() {

		/**
		 * - 1 - 生成一组随机数
		 */
		code = createRandomText();
		/**
		 * - 2 - 创建位图bitmap，画布canvas，初始化画笔Paint
		 */
		Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);// 创建指定位图的画布
		Paint paint = new Paint();// 新建画笔
		paint.setTextSize(FontSize);// 设置画笔大小
		base_padding_left = 20;
		// 循环n次（次数是code字符大小）
		for (int i = 0; i < code.length(); i++) {
			int color = RandomColor();// 随机一个颜色
			paint.setColor(color);// 设置颜色
			paint.setFakeBoldText(false);// 取消字体加粗
			float skewX = random.nextInt(11) / 10;
			skewX = random.nextBoolean() ? skewX : (-skewX);
			paint.setTextSkewX(skewX);// 设置字体倾斜方向（整数左，负数右）
			// 设置一个随机数（用于边距）
			int padding_top = base_padding_top
					+ random.nextInt(random_padding_top);
			// 绘制这个随机数
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					padding_top, paint);
			base_padding_left += random_padding_left;
		}
		for (int i = 0; i < xCode_Line_Number; i++) {
			mDrawLine(canvas, paint);
		}
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return bitmap;
	}
}
