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
 * @Todo TODO 生成随机验证码ver3.0
 * @package_name com.etang.libpack.codepass
 * @project_name EtangCodePass
 * @file_name CodePass.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class CodePass {
	public static String code = "";
	public static String char_code = "";
	private static String system_code = "1234567890zxcvbnmasdfg1234567890hjklpoiuytrewq1234567890QWERTYUIOPLKJHG1234567890FDSAZXCVBNM";

	private static void show_error_dialog(String e, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("出现错误");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage("哦，出现了一个问题！！！\n" + e);
		builder.setPositiveButton("确定", null);
		builder.show();
	}

	public static Bitmap createBitmap(Context context, int width_int,
			int height_int, int code_length, int text_size) {
		try {
			Bitmap bitmap = Bitmap.createBitmap(width_int, height_int,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			Random random = new Random();
			Paint paint = new Paint();
			paint.setTextSize(text_size);
			StringBuffer buffer = new StringBuffer();
			if (char_code.equals("")) {
				for (int i = 0; i < code_length; i++) {
					buffer.append(system_code.charAt(random.nextInt(system_code
							.length())));
				}
			} else {
				for (int i = 0; i < code_length; i++) {
					buffer.append(char_code.charAt(random.nextInt(char_code
							.length())));
				}
			}
			code = buffer.toString();
			int base_left = (canvas.getWidth() / code_length) - text_size;
			int base_top = 20;
			int random_left = 23;
			int random_top = 0;
			for (int i = 0; i < code.length(); i++) {
				paint.setAntiAlias(random.nextBoolean());
				paint.setTextSkewX(-random.nextInt(1));
				paint.setFakeBoldText(random.nextBoolean());
				paint.setColor(Color.rgb(random.nextInt(256),
						random.nextInt(256), random.nextInt(256)));
				random_top = base_top + random.nextInt(10);
				canvas.drawText(code.charAt(i) + "", base_left, random_top,
						paint);
				base_left += random_left + random.nextInt(10);
			}
			canvas.drawLine(random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()),
					random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()), paint);
			canvas.save(Canvas.ALL_SAVE_FLAG);
			canvas.restore();
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(context, "EtangPassCode组件出现问题", Toast.LENGTH_LONG)
					.show();
			show_error_dialog(e.toString(), context);
		}
		return null;
	}
}