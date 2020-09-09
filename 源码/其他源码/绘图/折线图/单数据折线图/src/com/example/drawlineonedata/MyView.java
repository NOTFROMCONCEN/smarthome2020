package com.example.drawlineonedata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.util.AttributeSet;
import android.view.View;
import com.example.drawlineonedata.MainActivity;

public class MyView extends View {
	public static HashMap<String, String> map = new HashMap<String, String>();
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	public static List<Float> data = new ArrayList<Float>();
	public static List<Float> Xlable = new ArrayList<Float>();
	public static List<Integer> Xnumber = new ArrayList<Integer>();
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xline = 400;
	private int Yline = 200;
	private int Xheight = 50;
	private int Yheight = 40;
	private int number = 0;
	private String[] Ylable = new String[6];
	private int max = 100;
	private MySQL mySQL;
	private SQLiteDatabase db;
	private Paint paint = new Paint();
	/**
	 * 更新UI的进程
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();// 刷新界面
				System.out.println("update");// 输出
				db.execSQL("insert into data (number,name,data)values(?,?,?)",
						new String[] { String.valueOf(number), "随机生成",
								MainActivity.random_number });// 存储绘图数据
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
					mySQL = new MySQL(getContext(), "info.db", null, 2);
					db = mySQL.getWritableDatabase();
					if (data.size() > 7) {
						data.remove(0);
					}
					if (Xlable.size() > 7) {
						Xlable.remove(0);
					}
					if (Xnumber.size() > 7) {
						Xnumber.remove(0);
					}
					data.add(Float.valueOf(MainActivity.random_number));
					Xlable.add(Float.valueOf(MainActivity.random_number));
					Xnumber.add(number);
					map = new HashMap<String, String>();
					map.put("number", String.valueOf(number));
					map.put("data", MainActivity.random_number);
					list.add(map);
					number++;
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setTextSize(20);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setStrokeWidth(1);
		/*
		 * 获取画布的宽和高
		 */
		System.out.println("高" + canvas.getHeight());
		System.out.println("宽" + canvas.getWidth());
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制Y轴刻度
		for (int i = 1; i < 6; i++) {
			// 绘制Y轴刻度线
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 10, Ypoint
					- i * Yheight, paint);
			// 绘制Y轴刻度数字
			canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight + 5,
					paint);
		}
		for (int i = 1; i < 8; i++) {
			canvas.drawLine(Xpoint + i * Xheight, Ypoint, Xpoint + i * Xheight,
					Ypoint - 10, paint);
		}
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					canvas.drawCircle(Xpoint + i * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
				} else {
					canvas.drawCircle(Xpoint + i * Xheight, Ypoint - Yline, 3,
							paint);
				}
				paint.setTextSize(15);
				canvas.drawText(Xlable.get(i).toString(), Xpoint + i * Xheight
						- 5, Ypoint + 15, paint);
				canvas.drawText("第" + Xnumber.get(i).toString() + "次", Xpoint
						+ i * Xheight - 5, Ypoint + 30, paint);
			}
			for (int i = 2; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint - Yline,
							Xpoint + i * Xheight, Ypoint - Yline, paint);
				}
				if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - Yline, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint - Yline,
							Xpoint + i * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
		}
	}
}
