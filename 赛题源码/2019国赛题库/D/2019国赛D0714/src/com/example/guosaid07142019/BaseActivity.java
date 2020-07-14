package com.example.guosaid07142019;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 数据采集
 * @package_name com.example.guosaid07142019
 * @project_name 2019国赛D0714
 * @file_name BaseActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class BaseActivity extends Activity {
	private TextView tv_gas;// 燃气
	private TextView tv_ill;// 光照
	private TextView tv_press;// 气压
	private TextView tv_hum;// 湿度
	private TextView tv_pm;// PM2.5
	private TextView tv_smo;// 烟雾
	private TextView tv_temp;// 温度
	private TextView tv_co;// Co2
	private TextView tv_per;// 人体红外
	private ImageView iv_back_button;// 返回按钮
	private ImageView iv_back_text;// 返回文本
	private TextView tv_time;
	private Random random = new Random();
	public static float temp, hum, ill, pm, press, co, per, gas, smo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		tv_time = (TextView) findViewById(R.id.tv_chuanganqi_time);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		iv_back_button = (ImageView) findViewById(R.id.iv_back_chuanganqi_button);
		iv_back_text = (ImageView) findViewById(R.id.iv_back_chuanganqi_text);
		// 关闭界面
		iv_back_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		iv_back_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// 设置字体颜色
		tv_time.setTextColor(Color.CYAN);
		// 数据采集
		ControlUtils.setUser("bizideal", "123456", MainActivity.IP_NUMBER);
		SocketClient.getInstance().creatConnect();
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
					tv_temp.setText(getdata.getTemperature());
					temp = Float.parseFloat(getdata.getTemperature());
				}
				if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
					tv_hum.setText(getdata.getHumidity());
					hum = Float.parseFloat(getdata.getHumidity());
				}
				if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
					tv_ill.setText(getdata.getIllumination());
					ill = Float.parseFloat(getdata.getIllumination());
				}
				if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
					tv_smo.setText(getdata.getSmoke());
					smo = Float.parseFloat(getdata.getSmoke());
				}
				if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
					tv_gas.setText(getdata.getGas());
					gas = Float.parseFloat(getdata.getGas());
				}
				if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
					tv_press.setText(getdata.getAirPressure());
					press = Float.parseFloat(getdata.getAirPressure());
				}
				if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
					tv_co.setText(getdata.getGas());
					co = Float.parseFloat(getdata.getGas());
				}
				if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
					tv_pm.setText(getdata.getPM25());
					pm = Float.parseFloat(getdata.getPM25());
				}
				if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
					if (getdata.getStateHumanInfrared().toString()
							.equals(ConstantUtil.CLOSE)) {
						tv_per.setText("无人");
						per = 0;
					} else {
						tv_per.setText("有人");
						per = 1;
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);

	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：获取时间
	 * 
	 * @时 间：上午8:51:13
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(MenuActivity.TIME);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
