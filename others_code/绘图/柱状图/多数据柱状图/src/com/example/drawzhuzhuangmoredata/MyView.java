package com.example.drawzhuzhuangmoredata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private HashMap<String, String> map = new HashMap<String, String>();
	private List<Float> data = new ArrayList<Float>();
	private List<Float> Xlable = new ArrayList<Float>();
	private List<Integer> Xnumber = new ArrayList<Integer>();
	private MySQL mySQL;
	private SQLiteDatabase db;
	private static String get_number = "";
	// 坐标系相关
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 50;
	private int Yheight = 40;
	private int Xline = 400;
	private int Yline = 200;
	private String[] Ylable = new String[6];
	private int Xlable_number = 0;
	// 画笔
	private Paint paint = new Paint();

	// 用于更新UI的线程
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();// 刷新UI
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < Ylable.length; i++) {
			Ylable[i] = String.valueOf(i * 20);
		}
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
					if (data.size() > 7) {
						data.remove(0);
					}
					if (Xlable.size() > 7) {
						Xlable.remove(0);
					}
					if (Xnumber.size() > 7) {
						Xnumber.remove(0);
					}
					data.add(Float.valueOf(get_number));
					Xlable.add(Float.valueOf(get_number));
					Xnumber.add(Xlable_number);
					map = new HashMap<String, String>();
					map.put("number", String.valueOf(Xlable_number));
					map.put("data", String.valueOf(get_number));
					list.add(map);
					Xlable_number++;
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	public static void get_number(String s) {
		// TODO Auto-generated method stub
		get_number = s;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);// 设置为线条风格
		paint.setTextSize(16);// 设置文本大小
		paint.setAntiAlias(true);// 设置抗锯齿
		paint.setStrokeWidth(1);// 设置线条宽度
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制Y轴刻度
		for (int i = 1; i < Ylable.length; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 10, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight + 5,
					paint);
		}
		// 绘制X轴刻度
		for (int i = 1; i < 8; i++) {
			canvas.drawLine(Xpoint + i * Xheight, Ypoint, Xpoint + i * Xheight,
					Ypoint + 10, paint);
		}
		/**
		 * 绘制柱状图
		 */
		if (data.size() > 0) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					canvas.drawRect(Xpoint + i * Xheight - 20,
							Ypoint - data.get(i) * Yheight / 20, Xpoint + i
									* Xheight + 20, Ypoint, paint);
				}
				if (data.get(i) > 100) {
					canvas.drawRect(Xpoint + i * Xheight - 20, Ypoint - Yline,
							Xpoint + i * Xheight + 20, Ypoint, paint);
				}
				canvas.drawText(data.get(i).toString(), Xpoint + i * Xheight,
						Ypoint + 20, paint);
			}
		}
	}
}
