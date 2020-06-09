package com.example.drawviewbing0607;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���Ʊ�״ͼ
 * @package_name com.example.drawviewbing0607
 * @project_name DrawView��״ͼ0607
 * @file_name MyView.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MyView extends View {
	// �洢�����õ�Float����List�б�
	private List<Float> data = new ArrayList<Float>();
	// ���������
	private Random random = new Random();
	// ����UI���߳�
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
		// �����߳������������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {// ����ѭ��
					try {
						// �����߳�1��
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// ���data�Ĵ�С����7
					if (data.size() > 7) {
						// �Ƴ���һλ
						data.remove(0);
					}
					// �������
					data.add(Float.valueOf(random.nextInt(256)));
					// ֪ͨhandler����UI
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();// �½�����
		paint.setColor(Color.BLACK);// ���û���Ĭ����ɫ
		paint.setStyle(Style.FILL);// ���û��ʷ��
		paint.setAntiAlias(true);// �����
		float jiaodu = -90;// Ĭ�ϽǶ� -90��
		float zong = 0;// ȫ�����ݴ�С
		// �½�RectF
		RectF rectF = new RectF(0, 0, getWidth(), getHeight());
		// �½���ɫ
		int[] color = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
				Color.GRAY, Color.GREEN, Color.RED, Color.YELLOW };
		// ���data�Ĵ�С
		if (data.size() > 1) {
			// ����ѭ��
			for (int i = 0; i < data.size(); i++) {
				zong = zong + data.get(i);
				// ���û�����ɫ
				paint.setColor(color[i]);
				// ����
				canvas.drawArc(rectF, jiaodu, data.get(i) * 360 / zong, true,
						paint);
				jiaodu = jiaodu + data.get(i) * 360 / zong;
			}
		}
	}
}
