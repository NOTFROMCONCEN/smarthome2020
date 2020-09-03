package com.example.shengsai07012019.view;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���������֤��
 * @package_name com.example.shengsai07012019.view
 * @project_name 2019ʡ��0701
 * @file_name CodeView.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class CodeView {
	// �����
	private static String all_code = "1234567890wqiopyegvbhjasdvbhjzxcpvoWPIUQEOGHRWAJHBVBDHJK";
	// �����������֤��
	public static String code = "";

	/**
	 * ����bitmap
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// �½�bitmap��������140��50��Config.ARGB_8888
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// ����һ���µĻ�������С�����̳������bitmap
		Canvas canvas = new Canvas(bitmap);
		// ���������
		Random random = new Random();
		// ��������
		Paint paint = new Paint();
		paint.setTextSize(20);
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
			paint.setAntiAlias(random.nextBoolean());
			paint.setTextSkewX(-random.nextInt(1));
			paint.setFakeBoldText(random.nextBoolean());
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			random_top = base_top + random.nextInt(20);
			canvas.drawText(code.charAt(i) + "", base_left, random_top, paint);
			base_left += random_left + random.nextInt(20);
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
