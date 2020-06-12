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
 * @Todo TODO ���������֤��
 * @package_name com.example.codepass8
 * @project_name CodePass8
 * @file_name PassCode.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class PassCode {
	// ��������������֤��
	public static String code;

	/**
	 * ����Bitmapλͼ
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// ����λͼ
		final Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// ���������߳�
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Random random = new Random();
				// ���������
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < 2; i++) {
					char code_char = (char) (Math.random() * 26 + 'a');// ����26��Сд��ĸ������һ��
					// �����ɵ���ĸ��������ɵ�������ӵ�StringBuffer��
					buffer.append(String.valueOf(code_char) + random.nextInt(9));
				}
				// ���������
				code = buffer.toString();
				// ��������
				Canvas canvas = new Canvas(bitmap);
				// ��������
				Paint paint = new Paint();
				// ���û���
				paint.setTextSize(20);// �ı���С
				paint.setAntiAlias(true);// �����
				// ���ñ߾�
				int base_left = 20;
				int base_top = 20;
				int random_left = 23;
				int random_top = 0;
				// ��ʼѭ�������ı�
				for (int i = 0; i < code.length(); i++) {
					// �����ɫ
					paint.setColor(Color.rgb(random.nextInt(256),
							random.nextInt(256), random.nextInt(256)));
					// �Ƿ�Ӵ�
					paint.setFakeBoldText(random.nextBoolean());
					// �Ƿ���б
					paint.setTextSkewX(-random.nextInt(1));
					// ������һ�����Ƶı߾�
					random_top = base_top + random.nextInt(10);
					// �����ı�
					canvas.drawText(code.charAt(i) + "", base_left, random_top,
							paint);
					// ����߾�
					base_left += random_left;
				}
				// �������һ����
				canvas.drawLine(random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()),
						random.nextInt(canvas.getWidth()),
						random.nextInt(canvas.getHeight()), paint);
				// ����
				canvas.save(Canvas.ALL_SAVE_FLAG);
				// ����
				canvas.restore();

			}
		}).start();
		return bitmap;
	}
}