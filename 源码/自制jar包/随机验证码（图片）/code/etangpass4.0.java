package com.etang.libpack.codepass;

import java.util.Random;

import android.R.layout;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 生成随机验证码ver4.0
 * @package_name com.etang.libpack.codepass
 * @project_name EtangCodePass
 * @file_name CodePass.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class CodePass {
	public static String code = "";// 最终输出的code
	public static String char_code = "";// 提供的一个可自定义验证码范围String数值
	private static String system_code = "1234567890zxcvbnmasdfg1234567890hjklpoiuytrewq1234567890QWERTYUIOPLKJHG1234567890FDSAZXCVBNM";

	/**
	 * 显示错误dialog
	 * 
	 * @param e
	 * @param context
	 */
	private static void show_error_dialog(String e, Context context) {
		// 新建
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// 设置标题
		builder.setTitle("出现错误");
		// 设置图标
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		// 设置内容
		builder.setMessage("哦，出现了一个问题！！！\n" + e);
		// 设置按钮
		builder.setPositiveButton("确定", null);
		// 显示dialog
		builder.show();
	}

	/**
	 * 
	 * 生成一个Bitmap（即随机验证码）
	 * 
	 * @param context
	 *            当前Context
	 * @param width_int
	 *            宽
	 * @param height_int
	 *            高
	 * @param code_length
	 *            随机验证码长度
	 * @param text_size
	 *            文本大小
	 * @return 返回bitmap或者null
	 */
	public static Bitmap createBitmap(Context context, int width_int,
			int height_int, int code_length, int text_size) {
		// try防止报错导致程序停止运行
		try {
			// 创建bitmap并配置
			Bitmap bitmap = Bitmap.createBitmap(width_int, height_int,
					Config.ARGB_8888);
			// 创建Canvas画布
			Canvas canvas = new Canvas(bitmap);
			// 创建随机数
			Random random = new Random();
			// 创建画笔
			Paint paint = new Paint();
			// 设置文本大小
			paint.setTextSize(text_size);
			// 新建StringBuffer用于存储生成的随机数
			StringBuffer buffer = new StringBuffer();
			if (char_code.equals("")) {
				// 如果用户没有自定义生成范围
				for (int i = 0; i < code_length; i++) {
					// 使用system_code生成随机数
					buffer.append(system_code.charAt(random.nextInt(system_code
							.length())));
				}
			} else {
				// 如果用户自定义生成范围
				for (int i = 0; i < code_length; i++) {
					// 使用char_code生成随机数
					buffer.append(char_code.charAt(random.nextInt(char_code
							.length())));
				}
			}
			// 赋值
			code = buffer.toString();
			// 设置默认左边距
			int base_left = (canvas.getWidth() / code_length) - text_size;
			// 设置默认顶边距
			int base_top = 20;
			// 设置随机左边距默认值
			int random_left = 23;
			// 设置随机顶边距默认值
			int random_top = 0;
			// 循环绘制随机验证码
			for (int i = 0; i < code.length(); i++) {
				// 随机设置画笔的抗锯齿
				paint.setAntiAlias(random.nextBoolean());
				// 随机设置画笔的倾斜
				paint.setTextSkewX(-random.nextInt(1));
				// 随机设置画笔的加粗
				paint.setFakeBoldText(random.nextBoolean());
				// 随机设置画笔的颜色
				paint.setColor(Color.rgb(random.nextInt(256),
						random.nextInt(256), random.nextInt(256)));
				random_top = base_top + random.nextInt(10);
				// 绘制
				canvas.drawText(code.charAt(i) + "", base_left, random_top,
						paint);
				base_left += random_left + random.nextInt(10);
			}
			// 绘制一条线
			canvas.drawLine(random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()),
					random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()), paint);
			// 全部保存画布
			canvas.save(Canvas.ALL_SAVE_FLAG);
			// 重置画布
			canvas.restore();
			// 返回bitmap
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
			// 提示出现错误
			Toast.makeText(context, "EtangPassCode组件出现问题", Toast.LENGTH_LONG)
					.show();
			// 显示错误提示框
			show_error_dialog(e.toString(), context);
		}
		// 如果try内运行出错的话直接返回null
		return null;
	}
}