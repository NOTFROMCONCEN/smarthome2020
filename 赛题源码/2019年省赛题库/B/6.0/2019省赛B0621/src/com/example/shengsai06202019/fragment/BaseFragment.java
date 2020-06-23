package com.example.shengsai06202019.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai06202019.R;
import com.example.shengsai06202019.tools.AppConfig;
import com.example.shengsai06202019.tools.DiyToast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 基本、数据采集、设备控制
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019省赛0620
 * @file_name BaseFragment.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class BaseFragment extends Fragment {

	private TextView tv_temp, tv_hum, tv_press, tv_pm, tv_smo, tv_gas, tv_per,
			tv_ill, tv_co, tv_base_time;
	private ToggleButton tg_cur, tg_door, tg_warm, tg_lamp1, tg_lamp2, tg_fan;
	private EditText et_send;
	private Button btn_send;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_base, null, false);
		initView(view);// 绑定控件
		handler.post(timeRunnable);// 启动线程
		/**
		 * 设备控制
		 */
		// 窗帘
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
		});
		// 门禁
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		// 风扇
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// 射灯1
		tg_lamp1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
		});
		// 射灯2
		tg_lamp2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
			}
		});
		// 报警灯
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// 红外转发按钮
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, et_send
							.getText().toString(), ConstantUtil.OPEN);
					DiyToast.showToast(getActivity(), "已发送："
							+ et_send.getText().toString());
				}
			}
		});
		return view;
	}

	/**
	 * 获取数据、设置时间线程
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// co2
			tv_co.setText(String.valueOf(AppConfig.co));
			// 燃气
			tv_gas.setText(String.valueOf(AppConfig.gas));
			// 湿度
			tv_hum.setText(String.valueOf(AppConfig.hum));
			// 光照
			tv_ill.setText(String.valueOf(AppConfig.ill));
			// PM2.5
			tv_pm.setText(String.valueOf(AppConfig.pm));
			// 气压
			tv_press.setText(String.valueOf(AppConfig.press));
			// 烟雾
			tv_smo.setText(String.valueOf(AppConfig.smo));
			// 温度
			tv_temp.setText(String.valueOf(AppConfig.temp));
			// 人体红外
			if (AppConfig.per == 1) {
				tv_per.setText("有人");
			} else {
				tv_per.setText("无人");
			}
			// 设置时间
			tv_base_time.setText(AppConfig.time);
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

	/**
	 * 绑定控件
	 * 
	 * @param view
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		tv_base_time = (TextView) view.findViewById(R.id.tv_base_time);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_lamp1 = (ToggleButton) view.findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) view.findViewById(R.id.tg_lamp2);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
	}
}
