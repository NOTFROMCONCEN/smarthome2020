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
 * @Todo TODO ���������֤��ver4.0
 * @package_name com.etang.libpack.codepass
 * @project_name EtangCodePass
 * @file_name CodePass.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class CodePass {
	public static String code = "";// ���������code
	public static String char_code = "";// �ṩ��һ�����Զ�����֤�뷶ΧString��ֵ
	private static String system_code = "1234567890zxcvbnmasdfg1234567890hjklpoiuytrewq1234567890QWERTYUIOPLKJHG1234567890FDSAZXCVBNM";

	/**
	 * ��ʾ����dialog
	 * 
	 * @param e
	 * @param context
	 */
	private static void show_error_dialog(String e, Context context) {
		// �½�
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// ���ñ���
		builder.setTitle("���ִ���");
		// ����ͼ��
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		// ��������
		builder.setMessage("Ŷ��������һ�����⣡����\n" + e);
		// ���ð�ť
		builder.setPositiveButton("ȷ��", null);
		// ��ʾdialog
		builder.show();
	}

	/**
	 * 
	 * ����һ��Bitmap���������֤�룩
	 * 
	 * @param context
	 *            ��ǰContext
	 * @param width_int
	 *            ��
	 * @param height_int
	 *            ��
	 * @param code_length
	 *            �����֤�볤��
	 * @param text_size
	 *            �ı���С
	 * @return ����bitmap����null
	 */
	public static Bitmap createBitmap(Context context, int width_int,
			int height_int, int code_length, int text_size) {
		// try��ֹ�����³���ֹͣ����
		try {
			// ����bitmap������
			Bitmap bitmap = Bitmap.createBitmap(width_int, height_int,
					Config.ARGB_8888);
			// ����Canvas����
			Canvas canvas = new Canvas(bitmap);
			// ���������
			Random random = new Random();
			// ��������
			Paint paint = new Paint();
			// �����ı���С
			paint.setTextSize(text_size);
			// �½�StringBuffer���ڴ洢���ɵ������
			StringBuffer buffer = new StringBuffer();
			if (char_code.equals("")) {
				// ����û�û���Զ������ɷ�Χ
				for (int i = 0; i < code_length; i++) {
					// ʹ��system_code���������
					buffer.append(system_code.charAt(random.nextInt(system_code
							.length())));
				}
			} else {
				// ����û��Զ������ɷ�Χ
				for (int i = 0; i < code_length; i++) {
					// ʹ��char_code���������
					buffer.append(char_code.charAt(random.nextInt(char_code
							.length())));
				}
			}
			// ��ֵ
			code = buffer.toString();
			// ����Ĭ����߾�
			int base_left = (canvas.getWidth() / code_length) - text_size;
			// ����Ĭ�϶��߾�
			int base_top = 20;
			// ���������߾�Ĭ��ֵ
			int random_left = 23;
			// ����������߾�Ĭ��ֵ
			int random_top = 0;
			// ѭ�����������֤��
			for (int i = 0; i < code.length(); i++) {
				// ������û��ʵĿ����
				paint.setAntiAlias(random.nextBoolean());
				// ������û��ʵ���б
				paint.setTextSkewX(-random.nextInt(1));
				// ������û��ʵļӴ�
				paint.setFakeBoldText(random.nextBoolean());
				// ������û��ʵ���ɫ
				paint.setColor(Color.rgb(random.nextInt(256),
						random.nextInt(256), random.nextInt(256)));
				random_top = base_top + random.nextInt(10);
				// ����
				canvas.drawText(code.charAt(i) + "", base_left, random_top,
						paint);
				base_left += random_left + random.nextInt(10);
			}
			// ����һ����
			canvas.drawLine(random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()),
					random.nextInt(canvas.getWidth()),
					random.nextInt(canvas.getHeight()), paint);
			// ȫ�����滭��
			canvas.save(Canvas.ALL_SAVE_FLAG);
			// ���û���
			canvas.restore();
			// ����bitmap
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
			// ��ʾ���ִ���
			Toast.makeText(context, "EtangPassCode�����������", Toast.LENGTH_LONG)
					.show();
			// ��ʾ������ʾ��
			show_error_dialog(e.toString(), context);
		}
		// ���try�����г���Ļ�ֱ�ӷ���null
		return null;
	}
}