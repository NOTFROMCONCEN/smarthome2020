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
		int width = canvas.getWidth() / 2;// 画布长
		int height = canvas.getHeight() / 2;// 画布宽
		int radius = canvas.getHeight() / 2 - 3;
		// 设置画笔
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);// 画笔颜色
		paint.setStyle(Style.STROKE);// 画笔线条
		paint.setStrokeWidth(3);// 画笔宽
		paint.setAntiAlias(true);// 抗锯齿
		// 绘制外层圆圈
		canvas.drawCircle(width, height, radius, paint);
		// 绘制小刻度（分钟）
		for (int i = 0; i < 60; i++) {
			canvas.save();// 保存画布
			canvas.rotate(i * 6, width, height);// 旋转画布
			// 绘制线条
			canvas.drawLine(width, height - radius, width,
					height - radius + 10, paint);
			canvas.restore();// 恢复画布
		}
		// 绘制大刻度（小时）
		for (int i = 0; i < 12; i++) {
			canvas.save();// 保存画布
			canvas.rotate(i * 30, width, height);// 旋转画布
			// 绘制线条
			canvas.drawLine(width, height - radius, width,
					height - radius + 20, paint);
			canvas.restore();// 恢复画布
		}
		// 绘制时间文本
		for (int i = 1; i < 13; i++) {
			// 设置画笔宽度
			paint.setStrokeWidth(1);
			canvas.save();// 保存画布
			canvas.rotate(i * 30, width, height);// 旋转画布
			// 绘制线条
			canvas.drawText(String.valueOf(i), width - 5, height - radius + 40,
					paint);
			canvas.restore();// 恢复画布
		}
		// 获取当前系统时间
		Calendar calendar = Calendar.getInstance();
		// 小时
		int hour = calendar.get(Calendar.HOUR);
		// 分钟
		int min = calendar.get(Calendar.MINUTE);
		// 秒
		int sec = calendar.get(Calendar.SECOND);
		// 设置画笔
		paint.setAntiAlias(true);
		paint.setStrokeWidth(15);
		paint.setStyle(Style.FILL);
		// 绘制时针
		canvas.save();
		canvas.rotate(hour * 30 + min / 60 * 30, width, height);
		canvas.drawLine(width, height, width, height - 40, paint);
		// 绘制分针
		paint.setStrokeWidth(10);
		canvas.rotate(min * 6, width, height);
		canvas.drawLine(width, height, width, height - 50, paint);
		// 绘制秒针
		paint.setStrokeWidth(5);
		canvas.rotate(sec * 6, width, height);
		canvas.drawLine(width, height, width, height - 60, paint);
		canvas.restore();
		// 绘制圆心
		canvas.drawCircle(width, height, 15, paint);
		invalidate();
	}
}
