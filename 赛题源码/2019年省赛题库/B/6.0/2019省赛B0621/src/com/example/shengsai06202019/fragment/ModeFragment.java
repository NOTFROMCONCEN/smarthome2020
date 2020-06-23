package com.example.shengsai06202019.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai06202019.R;
import com.example.shengsai06202019.tools.AppConfig;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ģʽ����
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019ʡ��B0620
 * @file_name ModeFragment.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class ModeFragment extends Fragment {
	private ToggleButton tg_mode_state;
	private RadioButton ra_day_mode, ra_night_mode, ra_dance_mode, ra_fd_mode;
	private boolean day_mode = false;
	private boolean dance_mode = false;
	private boolean night_mode = false;
	private boolean fd_mode = false;
	private boolean mode_state = false;
	int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mode, container, false);
		// �󶨿ؼ�
		ra_dance_mode = (RadioButton) view.findViewById(R.id.ra_dance_mode);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fd_mode = (RadioButton) view.findViewById(R.id.ra_fd_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		// ģʽ���ܿ���
		tg_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mode_state = true;
					if (ra_dance_mode.isChecked()) {// ����ģʽ
						day_mode = false;
						dance_mode = true;
						night_mode = false;
						fd_mode = false;
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								"1", ConstantUtil.OPEN);
					}
					if (ra_day_mode.isChecked()) {// ����ģʽ
						day_mode = true;
						dance_mode = false;
						night_mode = false;
						fd_mode = false;
						ControlUtils.control(ConstantUtil.Lamp,// �����
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						// ��������֮��������߷�ֹ����δ����
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						ControlUtils.control(ConstantUtil.Curtain,// �رմ���
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (day_mode || mode_state) {
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (AppConfig.smo > 400) {// �������400 �򿪷���
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									} else {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						}).start();
					}
					if (ra_fd_mode.isChecked()) {// ����ģʽ
						day_mode = false;
						dance_mode = false;
						night_mode = false;
						fd_mode = true;
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (fd_mode || mode_state) {
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (AppConfig.per >= 1) {// ���˿���
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
										// ��������֮��������߷�ֹ����δ����
										try {
											Thread.sleep(500);
										} catch (Exception e) {
											// TODO: handle exception
										}
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									} else {// ���˹ص�
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
										// ��������֮��������߷�ֹ����δ����
										try {
											Thread.sleep(500);
										} catch (Exception e) {
											// TODO: handle exception
										}
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						}).start();
					}
					if (ra_night_mode.isChecked()) {// ҹ��ģʽ
						day_mode = false;
						dance_mode = false;
						night_mode = true;
						fd_mode = false;
						ControlUtils.control(ConstantUtil.Curtain,// �򿪴���
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (night_mode || mode_state) {
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (AppConfig.ill < 200) {// ���մ���200��һ�����
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									} else if (AppConfig.ill > 500) {// ����500ȫ��
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
							}
						}).start();
					}
				} else {// �ر�ģʽ����ʱ�ر�����ģʽװ̬
					mode_state = false;
					day_mode = false;
					dance_mode = false;
					night_mode = false;
					fd_mode = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * ����ģʽ����
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (dance_mode) {
				if (msg.what % 4 == 0) {// 2���л�һ����ƿ���״̬
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
