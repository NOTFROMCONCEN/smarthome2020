package com.example.drawtimeclock;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {
	private Paint paint = new Paint();// ����
	private int strokewidth = 3;

	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(strokewidth);
		int width = canvas.getWidth() / 2;
		int height = canvas.getHeight() / 2;
		int radius = canvas.getHeight() / 2 - strokewidth;
		canvas.drawCircle(width, height, radius, paint);
		// ����С�̶�
		for (int i = 0; i < 60; i++) {
			canvas.save();
			canvas.rotate(i * 6, width, height);
			canvas.drawLine(width, height - radius, width,
					height - radius + 10, paint);
			canvas.restore();
		}
		// ���ƴ�̶�
		for (int i = 1; i < 13; i++) {
			canvas.save();
			canvas.rotate(i * 30, width, height);
			canvas.drawLine(width, height - radius, width,
					height - radius + 20, paint);
			canvas.restore();
		}
		// ����ʱ���־
		for (int i = 1; i < 13; i++) {
			canvas.save();
			canvas.rotate(i * 30, width, height);
			paint.setStrokeWidth(1);
			canvas.drawText(String.valueOf(i), width - 6, height - radius + 40,
					paint);
			canvas.restore();
		}
		// ����Բ��
		paint.setStyle(Style.FILL);
		canvas.drawCircle(width, height, 15, paint);
		// ʱ�뻭����ʽ
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(15);
		paint.setAntiAlias(true);
		// ��ȡϵͳʱ��
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		// ��ʱ��
		canvas.save();
		// ��������ת,����1:��ת�ĽǶ� 2:Χ������ת�ĵ������ת��X������ 3:Y������
		// ��һ������:�ͱ���4:30 ʱ���ƫ�ƽǶ�
		// 4*30=120��ʾ�ĵ��ʱ��ʱ�����ӱ������Ƕȵ�λ��
		// 30��/60��ʾռ�ݵİٷֱ� Ȼ����*30 ������ʮ����ռһ��(30��)�Ķ��ٽǶ�
		canvas.rotate(hour * 30 + minute / 60 * 30, width, height);
		canvas.drawLine(width, height + 15, width, height - 40, paint);
		canvas.restore();
		// ������
		paint.setStrokeWidth(10);
		canvas.save();
		// ����ÿ��һ�ֶ���6��
		canvas.rotate(minute * 6, width, height);
		canvas.drawLine(width, height + 12, width, height - 45, paint);
		canvas.restore();
		// ������
		canvas.save();
		paint.setStrokeWidth(5);
		// ʱ��ÿ��һ�ֶ���6��
		canvas.rotate(second * 6, width, height);
		canvas.drawLine(width, height + 9, width, height - 55, paint);
		canvas.restore();
		invalidate();// �ظ�����ondraw�ķ���,���ϵĻ���,ʹ��ʱ�ӳ��ֳ��߶���Ч��
	}
}
