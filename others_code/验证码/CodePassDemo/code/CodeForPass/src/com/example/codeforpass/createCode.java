package com.example.codeforpass;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * ��Ŀ����/�汾�ţ�RandomCode/v1.0 �� ����com.example.randomcode ��������(1)����һ�������;
 * (2)�����������񣬲�����canvas.drawText()��λͼ�� (3)����λͼ�ϻ��Ʒ��仯�ĸ������� �����ˣ�jiangdongguo
 * ����ʱ�䣺2015-6-6 ����3:22:41 ���͵�ַ��http://blog.csdn.net/u012637501
 */
public class createCode {

	private static final char[] CHARS = { '1', '2', '3', '4', '5', '6', '7',
			'0', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
			'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // Ԥ���������
	private static final int CodeLength = 4; // ���������
	private static final int LineNumber = 1; // ������Ŀ
	private static final int WIDTH = 140, HEIGHT = 80; // λͼ������
	private static final int FontSize = 40; // ����������С
	private static int base_padding_left;
	private static final int random_padding_left = 23, base_padding_top = 45,
			random_padding_top = 10;
	private static Random random = new Random();
	public static String code = "";

	/*********************************************************************************
	 * �� �� ����createRandomBitmap �������������������֤����ͼ Data ��2015-6-6[J]
	 *********************************************************************************/
	public static Bitmap createRandomBitmap() {
		/**
		 * (1)����һ�������
		 * */
		code = createRandomText(); // ����4�������
		/***
		 * (2)����λͼBitmap,����Canvas,��ʼ������Paint
		 * */
		Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888); // ����λͼ����ָ���䳤����
		Canvas canvas = new Canvas(bitmap); // ����ָ��λͼ�Ļ���
		canvas.drawColor(Color.GRAY); // ���û����ı���Ϊ��ɫ
		Paint paint = new Paint(); // ���廭��paint
		paint.setTextSize(FontSize); // ���û��������С
		/**
		 * (3)�����ĸ������������(��ɫ��λ�á���״)��λͼ
		 * */
		base_padding_left = 20;
		for (int i = 0; i < code.length(); i++) {
			// ����һ��������ķ��
			int color = RandomColor();
			paint.setColor(color); // ����(����)�����������ɫ
			paint.setFakeBoldText(false);// ���û���Ϊ�Ǵ���
			float skewX = random.nextInt(11) / 10;
			skewX = random.nextBoolean() ? skewX : (-skewX);
			paint.setTextSkewX(skewX); // ����������б����(������ʾ��б,������ʾ��б)
			// ����һ�������λ��
			// int padding_left = base_padding_left +
			// random.nextInt(random_padding_left);
			int padding_top = base_padding_top
					+ random.nextInt(random_padding_top);
			// ���Ƹ������
			canvas.drawText(code.charAt(i) + "", base_padding_left,
					padding_top, paint);
			base_padding_left += random_padding_left;
		}
		/**
		 * (4)��������
		 **/
		for (int i = 0; i < LineNumber; i++) {
			mdrawLine(canvas, paint);
		}
		canvas.save(Canvas.ALL_SAVE_FLAG); // ����
		canvas.restore();
		return bitmap;
	}

	/*********************************************************************************
	 * �� �� ����createRandomText ���������� Data ��2015-6-6[J]
	 *********************************************************************************/
	private static String createRandomText() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < CodeLength; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]); // CHARS�±��޶���0~CodeLength֮��
		}
		return buffer.toString(); // ����4�������
	}

	/********************************************************************************
	 * �� �� ����RandomColor ��������������һ�������ɫ Data ��2015-6-6[J]
	 *********************************************************************************/
	private static int RandomColor() {
		int red = random.nextInt(256); // ��ɫ��0~256֮��
		int green = random.nextInt(256); // ��ɫ��0~256֮��
		int blue = random.nextInt(256); // ��ɫ��0~256֮��
		return Color.rgb(red, green, blue); // �������������ɫֵ
	}

	/*********************************************************************************
	 * �� �� ����mdrawLine ��������������һ������,��������ǰ��������ǰ���� Data ��2015-6-6[J]
	 **********************************************************************************/
	private static void mdrawLine(Canvas canvas, Paint paint) {
		// a.���ø�������ɫ
		int color = RandomColor();
		paint.setColor(color);
		// b.���ø��������λ��(�����յ�,0~WIDTH,0~HEIGHT)
		int startX = random.nextInt(WIDTH);
		int startY = random.nextInt(HEIGHT);
		int stopX = random.nextInt(WIDTH);
		int stopY = random.nextInt(HEIGHT);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}
}
