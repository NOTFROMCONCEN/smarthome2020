package com.example.shengsai2019e0921.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2019e0921.R;
import com.example.shengsai2019e0921.tools.AppConfig;

public class BaseFragment extends Fragment {

	public static float getdata = 0;
	public static String number = "0";
	private View myView1, myView2;
	private LinearLayout ll_view;
	private RadioButton ra_open, ra_cls, ra_stop;
	private boolean tv_state = false, dvd_state = false, kt_state = false,
			lamp_l_state = false, lamp_r_state = false, cur_state = false,
			door_state = false, fan_state = false, is_hind = true;
	private RadioButton ra_tv, ra_dvd, ra_kt, ra_lamp_l, ra_lamp_r, ra_cur,
			ra_door, ra_fan;
	public static String string_name = "";
	private SeekBar sk_1;
	private TextView tv_l, tv_r;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, null, false);
		initView(view);
		handler.post(timeRunnable);
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ra_cur.isChecked()) {
					cur_state = true;
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
				if (ra_door.isChecked()) {
					door_state = true;
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				if (ra_fan.isChecked()) {
					fan_state = true;
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				if (ra_lamp_l.isChecked()) {
					lamp_l_state = true;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				if (ra_lamp_r.isChecked()) {
					lamp_r_state = true;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
				if (ra_dvd.isChecked()) {
					cur_state = true;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
							ConstantUtil.OPEN);
				}
				if (ra_kt.isChecked()) {
					cur_state = true;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.OPEN);
				}
				if (ra_tv.isChecked()) {
					cur_state = true;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
							ConstantUtil.OPEN);
				}
			}
		});
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ra_cur.isChecked()) {
					cur_state = false;
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
			}
		});

		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub.
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				if (ra_cur.isChecked()) {
					cur_state = false;
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
				if (ra_door.isChecked()) {
					door_state = false;
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
				if (ra_fan.isChecked()) {
					fan_state = false;
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				if (ra_lamp_l.isChecked()) {
					lamp_l_state = false;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
				if (ra_lamp_r.isChecked()) {
					lamp_r_state = false;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
				if (ra_dvd.isChecked()) {
					cur_state = false;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
							ConstantUtil.OPEN);
				}
				if (ra_kt.isChecked()) {
					cur_state = false;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.OPEN);
				}
				if (ra_tv.isChecked()) {
					cur_state = false;
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
							ConstantUtil.OPEN);
				}
			}
		});
		ll_view.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (is_hind) {
					myView1.setVisibility(View.GONE);
					myView2.setVisibility(View.VISIBLE);
					is_hind = false;
				} else {
					myView1.setVisibility(View.VISIBLE);
					myView2.setVisibility(View.GONE);
					is_hind = true;
				}
				return true;
			}
		});
		ll_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_showe_chose();
			}
		});
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (sk_1.getProgress() == 100 && number.equals("0")) {
					number = "100";
					tv_l.setText("0");
					tv_r.setText("100");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 100 && number.equals("100")) {
					number = "200";
					tv_l.setText("200");
					tv_r.setText("300");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 100 && number.equals("200")) {
					number = "300";
					tv_l.setText("200");
					tv_r.setText("300");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 100 && number.equals("0")) {
					number = "0";
					tv_l.setText("0");
					tv_r.setText("100");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 0 && number.equals("100")) {
					number = "0";
					tv_l.setText("100");
					tv_r.setText("200");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 0 && number.equals("200")) {
					number = "100";
					tv_l.setText("200");
					tv_r.setText("300");
					sk_1.setProgress(5);
				}
				if (sk_1.getProgress() == 0 && number.equals("300")) {
					number = "200";
					sk_1.setProgress(5);
				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		ll_view = (LinearLayout) view.findViewById(R.id.ll_view);
		myView1 = (View) view.findViewById(R.id.myview1);
		myView2 = (View) view.findViewById(R.id.myview2);
		tv_l = (TextView) view.findViewById(R.id.tv_number_l);
		tv_r = (TextView) view.findViewById(R.id.tv_number_r);
		sk_1 = (SeekBar) view.findViewById(R.id.sk_number);
		ra_cur = (RadioButton) view.findViewById(R.id.ra_cur);
		ra_door = (RadioButton) view.findViewById(R.id.ra_door);
		ra_dvd = (RadioButton) view.findViewById(R.id.ra_dvd);
		ra_fan = (RadioButton) view.findViewById(R.id.ra_fan);
		ra_kt = (RadioButton) view.findViewById(R.id.ra_kt);
		ra_lamp_l = (RadioButton) view.findViewById(R.id.ra_lamp_l);
		ra_lamp_r = (RadioButton) view.findViewById(R.id.ra_lamp_r);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		ra_tv = (RadioButton) view.findViewById(R.id.ra_tv);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
	}

	private void dialog_showe_chose() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.dialog_chose, null, false);
		builder.setView(view);
		final RadioButton ra_temp = (RadioButton) view
				.findViewById(R.id.ra_temp);
		final RadioButton ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		final RadioButton ra_press = (RadioButton) view
				.findViewById(R.id.ra_press);
		final RadioButton ra_pm = (RadioButton) view.findViewById(R.id.ra_pm);
		final RadioButton ra_per = (RadioButton) view.findViewById(R.id.ra_per);
		final RadioButton ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		final RadioButton ra_gas = (RadioButton) view.findViewById(R.id.ra_gas);
		final RadioButton ra_co = (RadioButton) view.findViewById(R.id.ra_temp);
		final RadioButton ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		if (string_name.equals("温度")) {
			ra_temp.setChecked(true);
		}
		if (string_name.equals("湿度")) {
			ra_hum.setChecked(true);
		}
		if (string_name.equals("烟雾")) {
			ra_smo.setChecked(true);
		}
		if (string_name.equals("气压")) {
			ra_press.setChecked(true);
		}
		if (string_name.equals("PM")) {
			ra_pm.setChecked(true);
		}
		if (string_name.equals("Co2")) {
			ra_co.setChecked(true);
		}
		if (string_name.equals("人体")) {
			ra_per.setChecked(true);
		}
		if (string_name.equals("光照")) {
			ra_ill.setChecked(true);
		}
		if (string_name.equals("燃气")) {
			ra_gas.setChecked(true);
		}
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (ra_co.isChecked()) {
					string_name = "Co2";
				}
				if (ra_gas.isChecked()) {
					string_name = "燃气";
				}
				if (ra_hum.isChecked()) {
					string_name = "湿度";
				}
				if (ra_ill.isChecked()) {
					string_name = "光照";
				}
				if (ra_per.isChecked()) {
					string_name = "人体";
				}
				if (ra_pm.isChecked()) {
					string_name = "PM";
				}
				if (ra_press.isChecked()) {
					string_name = "气压";
				}
				if (ra_smo.isChecked()) {
					string_name = "烟雾";
				}
				if (ra_temp.isChecked()) {
					string_name = "温度";
				}
			}
		});

		builder.show();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cur_state && door_state && dvd_state && fan_state && kt_state
					&& lamp_l_state && lamp_r_state && tv_state) {
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				System.out.println("报警灯开启");
			}
			if (string_name.equals("温度")) {
				getdata = AppConfig.temp;
			}
			if (string_name.equals("湿度")) {
				getdata = AppConfig.hum;
			}
			if (string_name.equals("烟雾")) {
				getdata = AppConfig.smo;
			}
			if (string_name.equals("气压")) {
				getdata = AppConfig.press;
			}
			if (string_name.equals("PM")) {
				getdata = AppConfig.pm;
			}
			if (string_name.equals("Co2")) {
				getdata = AppConfig.co;
			}
			if (string_name.equals("人体")) {
				getdata = AppConfig.per;
			}
			if (string_name.equals("光照")) {
				getdata = AppConfig.ill;
			}
			if (string_name.equals("燃气")) {
				getdata = AppConfig.gas;
			}
			handler.postDelayed(timeRunnable, 1000);
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