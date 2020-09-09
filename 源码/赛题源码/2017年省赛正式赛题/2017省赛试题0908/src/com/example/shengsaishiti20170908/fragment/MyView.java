package com.example.shengsaishiti20170908.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.transform.Templates;

import com.example.shengsaishiti20170908.tools.AppConfig;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private int Xpoint = 70;
	private int Ypoint = 430;
	private int Xheight = 75;
	private int Yheight = 50;
	private int Xline = 600;
	private int Yline = 300;

	private String[] Ylable = new String[5];

	private List<Float> data_temp = new ArrayList<Float>();
	private List<Float> data_hum = new ArrayList<Float>();
	private List<Float> data_gas = new ArrayList<Float>();
	private List<Float> data_smo = new ArrayList<Float>();

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
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (data_temp.size() > 7) {
						data_temp.remove(0);
					}
					if (data_gas.size() > 7) {
						data_gas.remove(0);
					}
					if (data_smo.size() > 7) {
						data_smo.remove(0);
					}
					if (data_hum.size() > 7) {
						data_hum.remove(0);
					}
					if (AppConfig.draw_state) {
						if (AppConfig.hum_state) {
							data_hum.add(BaseFragment.hum);
						}
						if (AppConfig.temp_state) {
							data_temp.add(BaseFragment.temp);
						}
						if (AppConfig.gas_state) {
							data_gas.add(BaseFragment.gas);
						}
						if (AppConfig.smo_state) {
							data_smo.add(BaseFragment.smo);
						}
					}
					// data.add(AppTools.temp);
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
		paint.setStrokeWidth(1);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + (i) * Xheight, Ypoint, 3, paint);
		}
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + 10, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight, paint);
		}
		paint.setColor(Color.BLACK);
		draw_hum(canvas, paint);
		paint.setColor(Color.BLUE);
		draw_gas(canvas, paint);
		paint.setColor(Color.GREEN);
		draw_smo(canvas, paint);
		paint.setColor(Color.RED);
		draw_temp(canvas, paint);
	}

	private void draw_hum(Canvas canvas, Paint paint) {
		if (data_hum.size() > 2) {
			for (int i = 2; i < data_hum.size(); i++) {
				if (data_hum.get(i - 1) >= 80 && data_hum.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_hum.get(i - 1) < 80 && data_hum.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_hum.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_hum.get(i - 1) >= 80 && data_hum.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_hum.get(i) * Yheight / 20,
							paint);
				}
				if (data_hum.get(i - 1) < 80 && data_hum.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_hum.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_hum.get(i) * Yheight / 20,
							paint);
				}
			}
		}
	}

	private void draw_gas(Canvas canvas, Paint paint) {
		if (data_gas.size() > 2) {
			for (int i = 2; i < data_gas.size(); i++) {
				if (data_gas.get(i - 1) >= 80 && data_gas.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_gas.get(i - 1) < 80 && data_gas.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_gas.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_gas.get(i - 1) >= 80 && data_gas.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_gas.get(i) * Yheight / 20,
							paint);
				}
				if (data_gas.get(i - 1) < 80 && data_gas.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_gas.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_gas.get(i) * Yheight / 20,
							paint);
				}
			}
		}
	}

	private void draw_smo(Canvas canvas, Paint paint) {
		if (data_smo.size() > 2) {
			for (int i = 2; i < data_smo.size(); i++) {
				if (data_smo.get(i - 1) >= 80 && data_smo.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_smo.get(i - 1) < 80 && data_smo.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_smo.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_smo.get(i - 1) >= 80 && data_smo.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_smo.get(i) * Yheight / 20,
							paint);
				}
				if (data_smo.get(i - 1) < 80 && data_smo.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_smo.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - data_smo.get(i) * Yheight / 20,
							paint);
				}
			}
		}
	}

	private void draw_temp(Canvas canvas, Paint paint) {
		if (data_temp.size() > 2) {
			for (int i = 2; i < data_temp.size(); i++) {
				if (data_temp.get(i - 1) >= 80 && data_temp.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight, Ypoint - Ylable.length * Yheight / 20,
							paint);
				}
				if (data_temp.get(i - 1) < 80 && data_temp.get(i) >= 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight,
							Ypoint - data_temp.get(i) * Yheight / 20, paint);
				}
				if (data_temp.get(i - 1) >= 80 && data_temp.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- Ylable.length * Yheight / 20, Xpoint + i
							* Xheight,
							Ypoint - data_temp.get(i) * Yheight / 20, paint);
				}
				if (data_temp.get(i - 1) < 80 && data_temp.get(i) < 80) {
					canvas.drawLine(Xpoint + (i - 1) * Xheight, Ypoint
							- data_temp.get(i - 1) * Yheight / 20, Xpoint + i
							* Xheight,
							Ypoint - data_temp.get(i) * Yheight / 20, paint);
				}
			}
		}
	}

}
