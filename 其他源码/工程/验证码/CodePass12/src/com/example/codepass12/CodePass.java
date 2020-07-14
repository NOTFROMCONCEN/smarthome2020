package com.example.codepass12;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;

public class CodePass {
	// ���������String
	public static String code_String = "";
	// �����
	private static String code_allString = "qwq87e944hsmjn894789to789yt8awseg8458QEW4R847EYFGJ84FGK8M74";

	/**
	 * ����һ����Bitmapλͼ
	 * 
	 * @return
	 */
	public static Bitmap createBitmap() {
		// ����һ��bitmapλͼ
		Bitmap bitmap = Bitmap.createBitmap(140, 50, Config.ARGB_8888);
		// ��������
		Canvas canvas = new Canvas(bitmap);
		// ���������
		Random random = new Random();
		// ��������
		Paint paint = new Paint();
		// ���ÿ����
		paint.setAntiAlias(true);
		// �����ı���С
		paint.setTextSize(20);
		// �½�StringBuffer
		StringBuffer buffer = new StringBuffer();
		// ��ʼѭ����������
		for (int i = 0; i < 4; i++) {
			// ��������ɵ������
			buffer.append(code_allString.charAt(random.nextInt(code_allString
					.length())));
		}
		// ��code_String��ֵ
		code_String = buffer.toString();
		// ����Ĭ����߾�
		int base_left = 20;
		// ����Ĭ�϶��߾�
		int base_top = 20;
		// ���������߾�
		int random_left = 23;
		// ����������߾�
		int random_top = 0;
		// ��ʼѭ�����������֤��
		for (int i = 0; i < code_String.length(); i++) {
			// ���������ɫ
			paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256),
					random.nextInt(256)));
			// �����Ƿ�Ӵ�
			paint.setFakeBoldText(random.nextBoolean());
			// ������б�Ƕ�
			paint.setTextSkewX(-random.nextInt(1));
			// ����������߾�
			random_top = base_top + random.nextInt(10);
			// �����ı�
			canvas.drawText(code_String.charAt(i) + "", base_left, random_top,
					paint);
			// ������һ������λ�õ���߾�
			base_left += random_left;
		}
		// �������һ����
		canvas.drawLine(random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()),
				random.nextInt(canvas.getWidth()),
				random.nextInt(canvas.getHeight()), paint);
		// ����ȫ������
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// ���û���
		canvas.restore();
		// ����һ��bitmapλͼ
		return bitmap;
	}

}
