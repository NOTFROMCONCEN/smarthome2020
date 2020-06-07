package com.example.shengsaia06062019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 绘制
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name MyView.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MyView extends View {

	private int Xpoint = 70;
	private int Ypoint = 430;
	private int Xheight = 100;
	private int Yheight = 70;
	private int Xline = 800;
	private int Yline = 350;

	private List<Float> data = new ArrayList<Float>();

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
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (data.size() > 8) {
						data.remove(0);
					}
					data.add(AppConfig.temp);
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(3);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		if (data.size() > 1) {
			System.out.println("data size:" + data.size());
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) >= 15 && data.get(i) >= 15) {
					Paint paint1 = new Paint();
					paint1.setAntiAlias(true);
					paint1.setColor(Color.RED);
					paint1.setStrokeWidth(3);
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint1);
					System.out.println("绘制1");
				}
				if (data.get(i - 1) < 15 && data.get(i) >= 15) {
					Paint paint1 = new Paint();
					paint1.setAntiAlias(true);
					paint1.setColor(Color.BLACK);
					paint1.setStrokeWidth(3);
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint1);
					System.out.println("绘制1");
				}
				if (data.get(i - 1) >= 15 && data.get(i) < 15) {
					Paint paint1 = new Paint();
					paint1.setAntiAlias(true);
					paint1.setColor(Color.BLACK);
					paint1.setStrokeWidth(3);
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint1);
					System.out.println("绘制1");
				}
				if (data.get(i - 1) < 15 && data.get(i) < 15) {
					Paint paint1 = new Paint();
					paint1.setAntiAlias(true);
					paint1.setColor(Color.BLACK);
					paint1.setStrokeWidth(3);
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint1);
					System.out.println("绘制1");
				}
			}
		}
	}
}
