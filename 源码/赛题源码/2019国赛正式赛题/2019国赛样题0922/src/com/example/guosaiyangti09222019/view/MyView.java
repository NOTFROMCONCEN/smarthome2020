package com.example.guosaiyangti09222019.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.provider.CalendarContract.Colors;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.guosaiyangti09222019.LinkActivity;
import com.example.guosaiyangti09222019.sql.MyDataBaseHelper;
import com.example.guosaiyangti09222019.sql.tools.AppConfig;

public class MyView extends View {
	private List<Float> data = new ArrayList<Float>();
	private RectF oval, oval2;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (data.size() > 6) {
						data.remove(0);
					}
					// if (AppConfig.ill != 0) {
					Random random = new Random();
					data.add((float) random.nextInt(800));
					// data.add(AppConfig.ill);
					// }
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		// oval = new RectF(left, top, right, bottom)
		oval = new RectF(100, 10, 240, 150);
		// canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint)
		for (int i = 1; i < data.size(); i++) {
			canvas.drawArc(oval, data.get(i - 1), data.get(i) / 360, true,
					paint);
		}
		// canvas.drawArc(oval, 0, 170, true, paint);
		paint.setColor(Color.WHITE);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		// 绘制一个无填充圆圈（外圆）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		// X轴，Y轴，大小
		canvas.drawCircle(169, 80, 70, paint);
		super.onDraw(canvas);
	}
}
