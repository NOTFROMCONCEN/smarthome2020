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
 * @Todo TODO ���������֤��
 * @package_name com.example.codeforpass3
 * @project_name CodeForPass3
 * @file_name CodeView.java
 */
public class Code {
	private static int HEIGHT = 50;// λͼ��
	private static int WIDTH = 140;// λͼ��
	private static final char[] CODE_CHARS = { '1', '2', '3', '4', '5', '1',
			'6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
			'x', 'z', 'l', 'k', 'j', 'h', 'g', 'f', 'd', 's', 'a', 'p', 'o',
			'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I',
			'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
			'M', 'N', 'B', 'V', 'C' };// �����
	private static final int xCode_Line_Number = 1;// ��������
	private static final int xCode_Random_NUMBER = 4;// ���������
	private static final Random random = new Random();// �����
	private static final int FontSize = 25;// ���������С
	private static int base_padding_left;// ��߾�
	private static final int base_padding_top = 30;// ���߾�
	private static final int random_padding_left = 23;// �����߾�
	private static final int random_padding_top = 10;// ������߾�
	public static String code = "";// ��������������

	/**
	 * ��Ҫ������ɵ���Ŀ
	 * 
	 * 1.createRandomText ���������
	 * 
	 * 2.RandomColor ���������ɫ
	 * 
	 * 3.mDrawLine ����һ���������
	 */

	// ������mDrawLine ����������һ���������
	private static void mDrawLine(Canvas canvas, Paint paint) {
		int color = RandomColor();
		paint.setColor(color);
		int startX = random.nextInt(WIDTH);// X = ��
		int startY = random.nextInt(HEIGHT);// Y = ��
		int endX = random.nextInt(WIDTH);
		int endY = random.nextInt(HEIGHT);
		canvas.drawLine(startX, startY, endX, endY, paint);
	}

	// ������RandomColor ����������һ����ɫ����������ɫɫֵ��Χ����0-256��
	private static int RandomColor() {
		int red = random.nextInt(256);// ��
		int green = random.nextInt(256);// ��
		int blue = random.nextInt(256);// ��
		return Color.rgb(red, green, blue);
	}

	// ������createRandomText ����������һ������������������趨��xCode_Random_NUMBER����
	private static String createRandomText() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < xCode_Random_NUMBER; i++) {
			builder.append(CODE_CHARS[random.nextInt(CODE_CHARS.length)]);
		}
		return builder.toString();
	}

	/**
	 * ������createRandomBitMap
	 * 
	 * ���������������֤����ͼ
	 * 
	 * @return
	 */

	public static Bitmap createRandomBitMap() {

		/**
		 * - 1 - ����һ�������
		 */
		code = createRandomText();
		/**
		 * - 2 - ����λͼbitmap������canvas����ʼ������Paint
		 */
		Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);// ����ָ��λͼ�Ļ���
		Paint paint = new Paint();// �½�����
		paint.setTextSize(FontSize);// ���û��ʴ�С
		base_padding_left = 20;
		// ѭ��n�Σ�������code�ַ���С��
		for (int i = 0; i < code.length(); i++) {
			int color = RandomColor();// ���һ����ɫ
			paint.setColor(color);// ������ɫ
			paint.setFakeBoldText(false);// ȡ������Ӵ�
			float skewX = random.nextInt(11) / 10;
			skewX = random.nextBoolean() ? skewX : (-skewX);
			paint.setTextSkewX(skewX);// ����������б���������󣬸����ң�
			// ����һ������������ڱ߾ࣩ
			int padding_top = base_padding_top
					+ random.nextInt(random_padding_top);
			// ������������
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
