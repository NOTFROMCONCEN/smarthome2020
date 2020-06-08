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
	private Paint paint = new Paint();// 画笔
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
		// 绘制小刻度
		for (int i = 0; i < 60; i++) {
			canvas.save();
			canvas.rotate(i * 6, width, height);
			canvas.drawLine(width, height - radius, width,
					height - radius + 10, paint);
			canvas.restore();
		}
		// 绘制大刻度
		for (int i = 1; i < 13; i++) {
			canvas.save();
			canvas.rotate(i * 30, width, height);
			canvas.drawLine(width, height - radius, width,
					height - radius + 20, paint);
			canvas.restore();
		}
		// 绘制时间标志
		for (int i = 1; i < 13; i++) {
			canvas.save();
			canvas.rotate(i * 30, width, height);
			paint.setStrokeWidth(1);
			canvas.drawText(String.valueOf(i), width - 6, height - radius + 40,
					paint);
			canvas.restore();
		}
		// 绘制圆心
		paint.setStyle(Style.FILL);
		canvas.drawCircle(width, height, 15, paint);
		// 时针画笔样式
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(15);
		paint.setAntiAlias(true);
		// 获取系统时间
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		// 画时针
		canvas.save();
		// 画布的旋转,参数1:旋转的角度 2:围绕着旋转的点进行旋转的X轴坐标 3:Y轴坐标
		// 第一个参数:就比如4:30 时针的偏移角度
		// 4*30=120表示四点的时候时针在钟表的这个角度的位置
		// 30分/60表示占据的百分比 然后再*30 就是三十分钟占一刻(30度)的多少角度
		canvas.rotate(hour * 30 + minute / 60 * 30, width, height);
		canvas.drawLine(width, height + 15, width, height - 40, paint);
		canvas.restore();
		// 画分针
		paint.setStrokeWidth(10);
		canvas.save();
		// 分针每走一分都走6度
		canvas.rotate(minute * 6, width, height);
		canvas.drawLine(width, height + 12, width, height - 45, paint);
		canvas.restore();
		// 画秒针
		canvas.save();
		paint.setStrokeWidth(5);
		// 时针每走一分都走6度
		canvas.rotate(second * 6, width, height);
		canvas.drawLine(width, height + 9, width, height - 55, paint);
		canvas.restore();
		invalidate();// 重复调用ondraw的方法,不断的绘制,使用时钟呈现出走动的效果
	}
}
