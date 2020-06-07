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
 * @Todo TODO 绘制饼状图
 * @package_name com.example.drawviewbing0607
 * @project_name DrawView饼状图0607
 * @file_name MyView.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MyView extends View {
	// 存储数据用的Float类型List列表
	private List<Float> data = new ArrayList<Float>();
	// 随机生成器
	private Random random = new Random();
	// 更新UI的线程
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
		// 启动线程用于填充数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {// 开启循环
					try {
						// 休眠线程1秒
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 如果data的大小大于7
					if (data.size() > 7) {
						// 移除第一位
						data.remove(0);
					}
					// 添加数据
					data.add(Float.valueOf(random.nextInt(256)));
					// 通知handler更新UI
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();// 新建画笔
		paint.setColor(Color.BLACK);// 设置画笔默认颜色
		paint.setStyle(Style.FILL);// 设置画笔风格
		paint.setAntiAlias(true);// 抗锯齿
		float jiaodu = -90;// 默认角度 -90°
		float zong = 0;// 全部数据大小
		// 新建RectF
		RectF rectF = new RectF(0, 0, getWidth(), getHeight());
		// 新建颜色
		int[] color = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
				Color.GRAY, Color.GREEN, Color.RED, Color.YELLOW };
		// 检测data的大小
		if (data.size() > 1) {
			// 开启循环
			for (int i = 0; i < data.size(); i++) {
				zong = zong + data.get(i);
				// 设置画笔颜色
				paint.setColor(color[i]);
				// 绘制
				canvas.drawArc(rectF, jiaodu, data.get(i) * 360 / zong, true,
						paint);
				jiaodu = jiaodu + data.get(i) * 360 / zong;
			}
		}
	}
}
