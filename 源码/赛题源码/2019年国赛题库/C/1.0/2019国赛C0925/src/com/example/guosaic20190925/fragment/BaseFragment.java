package com.example.guosaic20190925.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaic20190925.R;
import com.example.guosaic20190925.tools.DiyToast;

public class BaseFragment extends Fragment {

	private Button btn_send;
	private EditText et_send;
	private ToggleButton tg_cur, tg_warm, tg_door, tg_lamp1, tg_lamp2, tg_fan;
	private TextView tv_temp, tv_hum, tv_press, tv_smo, tv_gas, tv_ill, tv_pm,
			tv_co, tv_per;
	public static float temp, hum, press, smo, gas, ill, pm, co, per;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_base, null, false);
		initView(view);
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
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {

				}
			}
		});
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
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(getdata.getAirPressure())) {
					tv_press.setText(getdata.getAirPressure());
					press = Float.valueOf(getdata.getAirPressure());
				}
				if (!TextUtils.isEmpty(getdata.getCo2())) {
					tv_co.setText(getdata.getCo2());
					co = Float.valueOf(getdata.getCo2());
				}
				if (!TextUtils.isEmpty(getdata.getPM25())) {
					tv_pm.setText(getdata.getPM25());
					pm = Float.valueOf(getdata.getPM25());
				}
				if (!TextUtils.isEmpty(getdata.getGas())) {
					tv_gas.setText(getdata.getGas());
					gas = Float.valueOf(getdata.getGas());
				}
				if (!TextUtils.isEmpty(getdata.getHumidity())) {
					tv_hum.setText(getdata.getHumidity());
					hum = Float.valueOf(getdata.getHumidity());
				}
				if (!TextUtils.isEmpty(getdata.getIllumination())) {
					tv_ill.setText(getdata.getIllumination());
					ill = Float.valueOf(getdata.getIllumination());
				}
				if (!TextUtils.isEmpty(getdata.getSmoke())) {
					tv_smo.setText(getdata.getSmoke());
					smo = Float.valueOf(getdata.getSmoke());
				}
				if (!TextUtils.isEmpty(getdata.getTemperature())) {
					tv_temp.setText(getdata.getTemperature());
					temp = Float.valueOf(getdata.getTemperature());
				}
				if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
					per = Float.valueOf(getdata.getStateHumanInfrared());
					if (per == 1) {
						tv_per.setText("有人");
					} else {
						tv_per.setText("无人");
					}
				}

			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_lamp1 = (ToggleButton) view.findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) view.findViewById(R.id.tg_lamp2);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
	}
}
