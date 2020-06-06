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
 * @Todo TODO ���������֤��
 * @package_name com.example.textdemo0603
 * @project_name 0603TextDemo
 * @file_name PassCode.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class PassCode {
	private static Random random = new Random();// �������
	// �������
	private static String random_code = "123xzc456789q78qr9wr4we41gioitydQEWRQQWERTGFDS849BSD4YUO8P5463DFHBVC";
	public static String code = "";// ��������������

	/**
	 * ������λ����֤��
	 * 
	 * @return
	 */
	private static String mCode() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < 4; i++) {// ����Ĵ�
			buffer.append(random_code.charAt(random.nextInt(random_code
					.length())));// ��ÿһ�����ɵ��������ӽ�buffer
		}
		return buffer.toString();// ת����ʽ
	}

	public static Bitmap createBitmap() {
		// ����һ����λ�������֤��
		code = mCode();
		// ����һ��bitmap
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// ��������
		Canvas canvas = new Canvas(bitmap);
		// ��������
		Paint paint = new Paint();
		paint.setTextSize(20);// �����ı���С
		paint.setAntiAlias(true);// ���ÿ����
		// �趨Ĭ�ϱ߾�
		int base_padding_left = 20;// Ĭ����߾�
		int base_padding_top = 20;// Ĭ�϶��߾�
		int random_padding_left = 23;// �����߾ࣨ���Ƶڶ���������ã�
		int random_padding_top = 0;// ������߾ࣨ���Ƶڶ���������ã�
		for (int i = 0; i < code.length(); i++) {
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));// ���õ�ǰ i �Ļ�����ɫ
			paint.setFakeBoldText(random.nextBoolean());// �����ı��Ƿ�Ӵ�
			// �����Ƿ���б����б�Ƕ�
			float[] ske = { (float) 0.5, (float) 0.5, 0, 0 };
			paint.setTextSkewX(ske[random.nextInt(ske.length)]);// ���û�����б�Ƕ�
			random_padding_top = base_padding_top + random.nextInt(10);
			// ������������
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					random_padding_top, paint);
			// ������һ�����ʻ���λ��
			base_padding_left += random_padding_left;
		}
		// ����һ���������
		canvas.drawLine(random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()),
				random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()), paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);// ����ȫ��
		canvas.restore();// ���û���
		return bitmap;// ����bitmap
	}
}