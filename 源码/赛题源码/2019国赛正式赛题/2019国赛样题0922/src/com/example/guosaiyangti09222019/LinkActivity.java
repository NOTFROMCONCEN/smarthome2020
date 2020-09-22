package com.example.guosaiyangti09222019;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosaiyangti09222019.sql.MyDataBaseHelper;
import com.example.guosaiyangti09222019.sql.tools.AppConfig;
import com.example.guosaiyangti09222019.sql.tools.DiyToast;
import com.example.guosaiyangti09222019.sql.tools.GetTimeClass;

public class LinkActivity extends Activity {
	private CheckBox cb_mode, cb_link, cb_diy;
	private ListView lv_log;
	private Switch sw_link_state;
	private int i = 0, log_number = 0;;
	private boolean lj_mdoe = true, inhome_mode = false, mode_mode = false;
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_link_number;
	private Button btn_save, btn_get;
	private EditText et_shebei, et_dongzuo, et_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		initView();
		GetTimeClass.getTime();
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lj_mdoe = false;
					inhome_mode = true;
					cb_mode.setChecked(false);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					cb_mode.setChecked(true);
				} else {
					lj_mdoe = true;
					inhome_mode = false;
					cb_mode.setChecked(false);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					cb_mode.setChecked(true);
				}
			}
		});
		cb_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_diy.setChecked(false);
					cb_link.setChecked(false);
					cb_mode.setChecked(true);
					mode_mode = true;
					if (lj_mdoe) {
						System.out.println("离家模式");
						i = 0;
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (mode_mode && lj_mdoe) {
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (AppConfig.smo > 600
											|| AppConfig.per == 1) {
										i++;
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
										insert_intolog("报警灯", "打开");
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
										insert_intolog("风扇", "打开");
										if (i < 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
											insert_intolog("窗帘", "打开");
										}
									} else {
										i++;
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
										insert_intolog("报警灯", "打开");
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
										insert_intolog("风扇", "打开");
										if (i < 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
											insert_intolog("窗帘", "打开");
										}
									}
								}
							}
						}).start();
					}
					if (inhome_mode) {
						System.out.println("在家模式");
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								"1", ConstantUtil.OPEN);
						insert_intolog("空调", "打开");
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						insert_intolog("射灯", "打开");
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						insert_intolog("窗帘", "关闭");
					}

				} else {
					mode_mode = false;
				}
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cb_diy.isChecked()) {
					if (et_dongzuo.getText().toString().isEmpty()
							|| et_shebei.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "不能有空白项");
					} else {
						insert_diy(et_shebei.getText().toString(), et_dongzuo
								.getText().toString());
						DiyToast.showToast(getApplicationContext(), "存储成功");
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "请勾选指令控制");
				}
			}
		});
		cb_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_link.setChecked(false);
					cb_diy.setChecked(true);
					cb_mode.setChecked(false);
				} else {

				}
			}
		});
		cb_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_diy.setChecked(false);
					cb_mode.setChecked(false);
					if (et_link_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入参数");
						cb_link.setChecked(false);
					}
				}
			}
		});
		btn_get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入参数");
				} else {
					get_diy(et_get.getText().toString());
				}
			}
		});
		handler.post(timeRunnable);
		get_log_into_listview();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cb_link.isChecked()) {
				if (!et_link_number.getText().toString().isEmpty()) {
					if (sp_3.getSelectedItem().toString().equals("开")) {

						if (sp_2.getSelectedItem().toString().equals("<")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (AppConfig.temp < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									insert_intolog("风扇", "开");
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (AppConfig.hum < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									insert_intolog("风扇", "开");
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("≥")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (AppConfig.temp > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									insert_intolog("风扇", "开");
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (AppConfig.hum > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									insert_intolog("风扇", "开");
								}
							}
						}

					}
					if (sp_3.getSelectedItem().toString().equals("关")) {
						if (sp_2.getSelectedItem().toString().equals("<")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (AppConfig.temp < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									insert_intolog("风扇", "关");
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (AppConfig.hum < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									insert_intolog("风扇", "关");
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("≥")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (AppConfig.temp > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									insert_intolog("风扇", "关");
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (AppConfig.hum > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									insert_intolog("风扇", "关");
								}
							}
						}
					}
				} else {
					cb_link.setChecked(false);
					DiyToast.showToast(getApplicationContext(), "请输入数值");
				}
			}
			handler.postDelayed(timeRunnable, 2000);
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

	private void get_log_into_listview() {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(
				getApplicationContext(), "info.db", null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		final Cursor cursor = db.rawQuery("select * from log", null);
		if (cursor.getCount() != 0) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
							getApplicationContext(), R.layout.listview_text,
							cursor, new String[] { "number", "shebei", "state",
									"time" },
							new int[] { R.id.tv_number, R.id.tv_shebei,
									R.id.tv_state, R.id.tv_time });
					lv_log.setAdapter(simpleCursorAdapter);
				}
			});
		}
	}

	private void insert_intolog(String shebei, String state) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(
				getApplicationContext(), "info.db", null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from log", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			log_number = Integer.valueOf(cursor.getString(cursor
					.getColumnIndex("number")));
			log_number++;
		}
		db.execSQL("insert into log(number,shebei,state,time)values(?,?,?,?)",
				new String[] { String.valueOf(log_number), shebei, state,
						GetTimeClass.time });
		get_log_into_listview();
	}

	private void insert_diy(String shebei, String dongzuo) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(
				getApplicationContext(), "info.db", null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("insert into op_save(shebei,dongzuo)values(?,?)",
				new String[] { shebei, dongzuo });
	}

	private void get_diy(String shebei) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(
				getApplicationContext(), "info.db", null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from op_save where shebei = ?",
				new String[] { shebei });
		if (cursor.getCount() != 0) {
			if (cursor.moveToNext()) {
				DiyToast.showToast(getApplicationContext(),
						cursor.getString(cursor.getColumnIndex("dongzuo"))
								+ "---" + shebei);
			} else {
				DiyToast.showToast(getApplicationContext(), "未找到参数");
			}
		} else {
			DiyToast.showToast(getApplicationContext(), "未找到参数");
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_get = (Button) findViewById(R.id.btn_get);
		btn_save = (Button) findViewById(R.id.btn_save);
		et_dongzuo = (EditText) findViewById(R.id.et_dongzuo);
		et_shebei = (EditText) findViewById(R.id.et_shebei);
		et_get = (EditText) findViewById(R.id.et_get);
		cb_diy = (CheckBox) findViewById(R.id.cb_op_state);
		cb_link = (CheckBox) findViewById(R.id.cb_link_state);
		cb_mode = (CheckBox) findViewById(R.id.cb_mode_state);
		sw_link_state = (Switch) findViewById(R.id.sw_in_out_home);
		lv_log = (ListView) findViewById(R.id.listView1);
		et_link_number = (EditText) findViewById(R.id.et_number_get);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
	}
}
