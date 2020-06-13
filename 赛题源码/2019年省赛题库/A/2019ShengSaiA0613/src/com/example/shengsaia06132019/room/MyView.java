package com.example.shengsaia06132019.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.shengsaia06132019.tools.AppConfig;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ�������ͼView
 * @package_name com.example.shengsaia06132019.room
 * @project_name 2019ShengSaiA0613
 * @file_name MyView.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MyView extends View {

	private List<Float> data = new ArrayList<Float>();

	private int Xpoint = 100;
	private int Ypoint = 350;
	private int Xheight = 50;
	private int Yheight = 40;
	private int Xline = 400;
	private int Yline = 200;
	/**
	 * ˢ��ui�߳�
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();// ˢ�½���
			}
		}
	};

	/**
	 * ���췽��
	 * 
	 * @param context
	 * @param attrs
	 */
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
					}
					if (data.size() >= 9) {
						data.remove(0);
					}
					data.add(AppConfig.temp);
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	/**
	 * ���Ʒ���
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// ���û���
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLACK);
		// ����XY��
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// ��������ͼ
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				// �жϴ�С
				if (data.get(i - 1) < 15 && data.get(i) < 15) {
					paint.setColor(Color.BLACK);// ������ɫ
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
				if (data.get(i - 1) >= 15 && data.get(i) < 15) {
					paint.setColor(Color.BLACK);// ������ɫ
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
				if (data.get(i - 1) < 15 && data.get(i) >= 15) {
					paint.setColor(Color.BLACK);// ������ɫ
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
				if (data.get(i - 1) >= 15 && data.get(i) >= 15) {
					paint.setColor(Color.RED);// ������ɫ
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
			}
		}
	}
}
