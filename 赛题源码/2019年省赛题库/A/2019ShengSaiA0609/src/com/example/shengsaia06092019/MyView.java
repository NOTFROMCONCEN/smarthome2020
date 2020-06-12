package com.example.shengsaia06092019;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private int Xpoint = 100;
	private int Ypoint = 400;
	private int Xheight = 100;
	private int Yheight = 60;
	private int Xline = 800;
	private int Yline = 300;
	private Paint paint = new Paint();
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
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (data.size() > 7) {
					data.remove(0);
				}
				data.add(AppConfig.temp);
				Random random = new Random();
				data.add(Float.valueOf(random.nextInt(256)));
				handler.sendEmptyMessage(0x1234);
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.FILL);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - data.get(i) * Yheight
									/ 20, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint - Yline,
							Xpoint + i * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
				if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, Xpoint + i
									* Xheight, Ypoint - Yline, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint - Yline,
							Xpoint + i * Xheight, Ypoint - Yline, paint);
				}
			}
		}
	}
}