package com.example.shengsaib06292019.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.shengsaib06292019.tools.AppConfig;

public class MyView extends View {

	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 100;
	private int Yheight = 50;
	private int Xline = 400;
	private int number = 0;
	private String[] Ylable = new String[5];
	private List<Integer> Xlable = new ArrayList<Integer>();
	private List<Float> data_temp = new ArrayList<Float>();
	private List<Float> data_ill = new ArrayList<Float>();

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 5; i++) {
			Ylable[i] = String.valueOf(i * 100);
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
					if (data_ill.size() > 3) {
						data_ill.remove(0);
					}
					if (data_temp.size() > 3) {
						data_temp.remove(0);
					}
					if (Xlable.size() > 3) {
						Xlable.remove(0);
					}
					data_ill.add(AppConfig.ill);
					data_temp.add(AppConfig.temp);
					number++;
					Xlable.add(number);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		canvas.drawText("Í¼±í", (Xpoint + Xline) / 2, Ypoint - Yheight * 5, paint);
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + Xline,
					Ypoint - i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 50, Ypoint - i * Yheight, paint);
		}
		if (Xlable.size() > 1) {
			paint.setColor(Color.BLACK);
			for (int i = 0; i < Xlable.size(); i++) {
				canvas.drawText(String.valueOf(Xlable.get(i)), Xpoint + i
						* Xheight + 40, Ypoint + 20, paint);
			}
		}
		if (data_temp.size() > 1) {
			paint.setColor(Color.BLUE);
			for (int i = 0; i < data_temp.size(); i++) {
				if (data_temp.get(i) <= 400) {
					canvas.drawRect(Xpoint + i * Xheight + 10, Ypoint
							- data_temp.get(i) * Yheight / 100, Xpoint + i
							* Xheight + 30, Ypoint, paint);
				} else {
					canvas.drawRect(Xpoint + i * Xheight + 10, Ypoint - Yheight
							* 4, Xpoint + i * Xheight + 30, Ypoint, paint);
				}
			}
		}
		if (data_ill.size() > 1) {
			paint.setColor(Color.RED);
			for (int i = 0; i < data_ill.size(); i++) {
				if (data_ill.get(i) <= 400) {
					canvas.drawRect(Xpoint + i * Xheight + 40, Ypoint
							- data_ill.get(i) * Yheight / 100, Xpoint + i
							* Xheight + 60, Ypoint, paint);
				} else {
					canvas.drawRect(Xpoint + i * Xheight + 40, Ypoint - Yheight
							* 4, Xpoint + i * Xheight + 60, Ypoint, paint);
				}
			}
		}
		MyView.this.invalidate();
	}
}
