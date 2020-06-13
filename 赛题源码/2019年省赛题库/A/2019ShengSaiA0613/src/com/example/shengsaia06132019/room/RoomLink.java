package com.example.shengsaia06132019.room;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaia06132019.R;
import com.example.shengsaia06132019.tools.AppConfig;
import com.example.shengsaia06132019.tools.DiyToast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��������
 * @package_name com.example.shengsaia06132019.room
 * @project_name 2019ShengSaiA0613
 * @file_name RoomLink.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class RoomLink extends Activity {

	private TextView tv_link_roomnumber;
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private Switch sw_link;
	private EditText et_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_link);
		setTitle("��������");
		initView();
		tv_link_roomnumber.setText("����ţ�" + AppConfig.roomnumber);
		/**
		 * ����״̬
		 */
		sw_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						handler.removeCallbacks(timeRunnable);
						sw_link.setChecked(false);
						DiyToast.showToast(getApplicationContext(), "��������ֵ");
					} else {
						handler.post(timeRunnable);
					}
				} else {
					handler.removeCallbacks(timeRunnable);
				}
			}
		});
	}

	/**
	 * ��
	 */
	private void open() {
		if (sp_3.getSelectedItem().toString().equals("����")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("���")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("����")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
	}

	/**
	 * �ر�
	 */
	private void close() {
		if (sp_3.getSelectedItem().toString().equals("����")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("���")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("����")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
	}

	/**
	 * �ж��߳�
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (et_number.getText().toString().isEmpty()) {
				handler.removeCallbacks(timeRunnable);
				sw_link.setChecked(false);
				DiyToast.showToast(getApplicationContext(), "��������ֵ");
			} else {
				int number = Integer.valueOf(et_number.getText().toString());
				if (sp_1.getSelectedItem().toString().equals("�ն�")) {
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number > AppConfig.ill) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number < AppConfig.ill) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
				}
				if (sp_1.getSelectedItem().toString().equals("�¶�")) {
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number > AppConfig.temp) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number < AppConfig.temp) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
				}
				if (sp_1.getSelectedItem().toString().equals("ʪ��")) {
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number > AppConfig.hum) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
					if (sp_2.getSelectedItem().toString().equals("<")
							&& number < AppConfig.hum) {
						if (sp_4.getSelectedItem().toString().equals("��")) {
							open();
						}
						if (sp_4.getSelectedItem().toString().equals("��")) {
							close();
						}
					}
				}
			}
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

	private void initView() {
		// TODO Auto-generated method stub
		tv_link_roomnumber = (TextView) findViewById(R.id.tv_link_roomnumber);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		sw_link = (Switch) findViewById(R.id.sw_link);
		et_number = (EditText) findViewById(R.id.et_number);
	}
}
