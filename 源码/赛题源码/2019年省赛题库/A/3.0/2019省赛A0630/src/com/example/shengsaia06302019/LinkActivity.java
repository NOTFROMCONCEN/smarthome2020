package com.example.shengsaia06302019;

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
import com.example.shengsaia06302019.tools.AppTools;
import com.example.shengsaia06302019.tools.DiyToast;

public class LinkActivity extends Activity {

	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_number;
	private Switch sw_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		setTitle("��������");// ����ActionBar��title�ı�
		((TextView) findViewById(R.id.tv_link_roomnumber)).setText("����ţ�"
				+ AppTools.room_number);// ���÷����
		initView();// �󶨿ؼ�
		// ����������ť
		sw_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {// ����ı���Ϊ��
						DiyToast.showToast(getApplicationContext(), "��������ֵ");
						sw_link.setChecked(false);// �رտ���
					} else {
						handler.post(timeRunnable);// �����߳�
					}
				} else {
					handler.removeCallbacks(timeRunnable);// �ر��߳�
				}
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (et_number.getText().toString().isEmpty()) {
				sw_link.setChecked(false);
				DiyToast.showToast(getApplicationContext(), "��������ֵ");
				handler.removeCallbacks(timeRunnable);
			} else {

				if (sp_1.getSelectedItem().toString().equals("ʪ��")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppTools.hum > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					}
				}

				if (sp_1.getSelectedItem().toString().equals("�¶�")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppTools.temp > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					}
				}

				if (sp_1.getSelectedItem().toString().equals("�ն�")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppTools.ill > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("���")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("����")
								&& sp_4.getSelectedItem().toString()
										.equals("��")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					}
				}
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

	/**
	 * �󶨿ؼ�
	 */
	private void initView() {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_number = (EditText) findViewById(R.id.et_number);
		sw_link = (Switch) findViewById(R.id.switch1);
	}
}
