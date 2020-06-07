package com.example.drawviewbing0606;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	List<Float> data = new ArrayList<Float>();
	private Random random = new Random();

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
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
					}
					if (data.size() > 6) {
						data.remove(0);
					}
					data.add(Float.valueOf(random.nextInt(400)));
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		float jiaodu = -90, zong = 0;
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		RectF rectF = new RectF(0, 0, getWidth(), getHeight());
		int[] color = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
				Color.GRAY, Color.GREEN, Color.LTGRAY };
		if (data.size() > 1) {
			for (int i = 0; i < data.size(); i++) {
				zong = zong + data.get(i);
				paint.setColor(color[i]);
				canvas.drawArc(rectF, jiaodu, data.get(i) * 360 / zong, true,
						paint);
				jiaodu = jiaodu + data.get(i) * 360 / zong;
			}
		}
	}
}