package com.example.shengsai07012019.view;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class TimeClockView extends View {

	public TimeClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int width = canvas.getWidth() / 2;// ������
		int height = canvas.getHeight() / 2;// ������
		int radius = canvas.getHeight() / 2 - 3;
		// ���û���
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);// ������ɫ
		paint.setStyle(Style.STROKE);// ��������
		paint.setStrokeWidth(3);// ���ʿ�
		paint.setAntiAlias(true);// �����
		// �������ԲȦ
		canvas.drawCircle(width, height, radius, paint);
		// ����С�̶ȣ����ӣ�
		for (int i = 0; i < 60; i++) {
			canvas.save();// ���滭��
			canvas.rotate(i * 6, width, height);// ��ת����
			// ��������
			canvas.drawLine(width, height - radius, width,
					height - radius + 10, paint);
			canvas.restore();// �ָ�����
		}
		// ���ƴ�̶ȣ�Сʱ��
		for (int i = 0; i < 12; i++) {
			canvas.save();// ���滭��
			canvas.rotate(i * 30, width, height);// ��ת����
			// ��������
			canvas.drawLine(width, height - radius, width,
					height - radius + 20, paint);
			canvas.restore();// �ָ�����
		}
		// ����ʱ���ı�
		for (int i = 1; i < 13; i++) {
			// ���û��ʿ��
			paint.setStrokeWidth(1);
			canvas.save();// ���滭��
			canvas.rotate(i * 30, width, height);// ��ת����
			// ��������
			canvas.drawText(String.valueOf(i), width - 5, height - radius + 40,
					paint);
			canvas.restore();// �ָ�����
		}
		// ��ȡ��ǰϵͳʱ��
		Calendar calendar = Calendar.getInstance();
		// Сʱ
		int hour = calendar.get(Calendar.HOUR);
		// ����
		int min = calendar.get(Calendar.MINUTE);
		// ��
		int sec = calendar.get(Calendar.SECOND);
		// ���û���
		paint.setAntiAlias(true);
		paint.setStrokeWidth(15);
		paint.setStyle(Style.FILL);
		// ����ʱ��
		canvas.save();
		canvas.rotate(hour * 30 + min / 60 * 30, width, height);
		canvas.drawLine(width, height, width, height - 40, paint);
		// ���Ʒ���
		paint.setStrokeWidth(10);
		canvas.rotate(min * 6, width, height);
		canvas.drawLine(width, height, width, height - 50, paint);
		// ��������
		paint.setStrokeWidth(5);
		canvas.rotate(sec * 6, width, height);
		canvas.drawLine(width, height, width, height - 60, paint);
		canvas.restore();
		// ����Բ��
		canvas.drawCircle(width, height, 15, paint);
		invalidate();
	}
}
