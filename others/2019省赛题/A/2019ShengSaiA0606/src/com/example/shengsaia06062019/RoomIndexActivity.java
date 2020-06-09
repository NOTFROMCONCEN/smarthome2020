package com.example.shengsaia06062019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shengsaia06062019.sql.MySQL;
import com.example.shengsaia06062019.tools.SocketLinkGetData;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 主页
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name RoomIndexActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class RoomIndexActivity extends Activity implements OnClickListener {

	private Button btn_1, btn_2, btn_3, btn_4;
	private View view_1, view_2, view_3, view_4;
	// 配置状态
	private boolean base_state = true;
	private boolean link_state = false;
	private boolean control_state = false;
	private boolean draw_state = false;
	// 810X
	private LinearLayout line_8101;
	private LinearLayout line_8102;
	private LinearLayout line_8103;
	private LinearLayout line_8104;
	// 820X
	private LinearLayout line_8201;
	private LinearLayout line_8202;
	private LinearLayout line_8203;
	private LinearLayout line_8204;
	// 830X
	private LinearLayout line_8301;
	private LinearLayout line_8302;
	private LinearLayout line_8303;
	private LinearLayout line_8304;
	// 840X
	private LinearLayout line_8401;
	private LinearLayout line_8402;
	private LinearLayout line_8403;
	private LinearLayout line_8404;
	// 数据库
	MySQL mySQL;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_index);
		setTitle("前台管理");
		initView();
		SocketLinkGetData.start_link_server(RoomIndexActivity.this,
				AppConfig.ip);// 开启网络连接和数据采集
		check_room_state();// 检查房间状态
		// 设置按钮切换状态
		// 基本
		btn_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.VISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				base_state = true;
				link_state = false;
				control_state = false;
				draw_state = false;
			}
		});
		// 联动
		btn_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.VISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				base_state = false;
				link_state = true;
				control_state = false;
				draw_state = false;
			}
		});
		// 控制
		btn_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.VISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				base_state = false;
				link_state = false;
				control_state = true;
				draw_state = false;
			}
		});
		// 联动
		btn_4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.VISIBLE);
				base_state = false;
				link_state = false;
				control_state = false;
				draw_state = true;
			}
		});

	}

	private void check_room_state() {
		// TODO Auto-generated method stub
		/**
		 * 开始循环查找房间状态
		 */
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				Cursor cursor = db.rawQuery(
						"select * from room_8" + String.valueOf(i) + "0"
								+ String.valueOf(j), null);
				if (cursor.getCount() != 0) {
					cursor.moveToLast();
					String state = cursor.getString(cursor
							.getColumnIndex("room_state"));
					System.out.println(state);
					if (i == 1) {
						if (j == 1) {
							// 8101
							if (state.equals("0")) {// 未入住
								line_8101.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8101.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8101.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 2) {
							// 8102
							if (state.equals("0")) {// 未入住
								line_8102.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8102.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8102.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 3) {
							// 8103
							if (state.equals("0")) {// 未入住
								line_8103.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8103.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8103.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 4) {
							// 8104
							if (state.equals("0")) {// 未入住
								line_8104.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8104.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8104.setBackgroundColor(Color.GRAY);
							}
						}
					}
					if (i == 2) {
						if (j == 1) {
							// 8101
							if (state.equals("0")) {// 未入住
								line_8201.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8201.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8201.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 2) {
							// 8102
							if (state.equals("0")) {// 未入住
								line_8202.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8202.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8202.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 3) {
							// 8103
							if (state.equals("0")) {// 未入住
								line_8203.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8203.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8203.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 4) {
							// 8104
							if (state.equals("0")) {// 未入住
								line_8204.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8204.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8204.setBackgroundColor(Color.GRAY);
							}
						}
					}
					if (i == 3) {

						if (j == 1) {
							// 8101
							if (state.equals("0")) {// 未入住
								line_8301.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8301.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8301.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 2) {
							// 8102
							if (state.equals("0")) {// 未入住
								line_8302.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8302.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8302.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 3) {
							// 8103
							if (state.equals("0")) {// 未入住
								line_8303.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8303.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8303.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 4) {
							// 8104
							if (state.equals("0")) {// 未入住
								line_8304.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8304.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8304.setBackgroundColor(Color.GRAY);
							}
						}

					}
					if (i == 4) {

						if (j == 1) {
							// 8101
							if (state.equals("0")) {// 未入住
								line_8401.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8401.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8401.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 2) {
							// 8102
							if (state.equals("0")) {// 未入住
								line_8402.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8402.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8402.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 3) {
							// 8103
							if (state.equals("0")) {// 未入住
								line_8403.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8403.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8403.setBackgroundColor(Color.GRAY);
							}
						}
						if (j == 4) {
							// 8104
							if (state.equals("0")) {// 未入住
								line_8404.setBackgroundColor(Color.GREEN);
							}
							if (state.equals("1")) {// 已入住
								line_8404.setBackgroundColor(Color.RED);
							}
							if (state.equals("2")) {// 未打扫
								line_8404.setBackgroundColor(Color.GRAY);
							}
						}

					}
				}
			}
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		line_8101 = (LinearLayout) findViewById(R.id.line_8101);
		line_8102 = (LinearLayout) findViewById(R.id.line_8102);
		line_8103 = (LinearLayout) findViewById(R.id.line_8103);
		line_8104 = (LinearLayout) findViewById(R.id.line_8104);
		line_8201 = (LinearLayout) findViewById(R.id.line_8201);
		line_8202 = (LinearLayout) findViewById(R.id.line_8202);
		line_8203 = (LinearLayout) findViewById(R.id.line_8203);
		line_8204 = (LinearLayout) findViewById(R.id.line_8204);
		line_8301 = (LinearLayout) findViewById(R.id.line_8301);
		line_8302 = (LinearLayout) findViewById(R.id.line_8302);
		line_8303 = (LinearLayout) findViewById(R.id.line_8303);
		line_8304 = (LinearLayout) findViewById(R.id.line_8304);
		line_8401 = (LinearLayout) findViewById(R.id.line_8401);
		line_8402 = (LinearLayout) findViewById(R.id.line_8402);
		line_8403 = (LinearLayout) findViewById(R.id.line_8403);
		line_8404 = (LinearLayout) findViewById(R.id.line_8404);
		line_8101.setOnClickListener(this);
		line_8102.setOnClickListener(this);
		line_8103.setOnClickListener(this);
		line_8104.setOnClickListener(this);
		line_8201.setOnClickListener(this);
		line_8202.setOnClickListener(this);
		line_8203.setOnClickListener(this);
		line_8204.setOnClickListener(this);
		line_8301.setOnClickListener(this);
		line_8302.setOnClickListener(this);
		line_8303.setOnClickListener(this);
		line_8304.setOnClickListener(this);
		line_8401.setOnClickListener(this);
		line_8402.setOnClickListener(this);
		line_8403.setOnClickListener(this);
		line_8404.setOnClickListener(this);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		view_1 = (View) findViewById(R.id.view_1);
		view_2 = (View) findViewById(R.id.view_2);
		view_3 = (View) findViewById(R.id.view_3);
		view_4 = (View) findViewById(R.id.view_4);
		mySQL = new MySQL(RoomIndexActivity.this, "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_room_index, menu);
		return true;
	}

	private void show_dialog_base(final String room_number) {
		// TODO Auto-generated method stub
		System.out.println(room_number);
		AlertDialog.Builder builder = new AlertDialog.Builder(
				RoomIndexActivity.this);
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.layout_base_dialog, null, false);
		builder.setView(view);
		final TextView tv_numebr = (TextView) view
				.findViewById(R.id.tv_baseroom_number);
		tv_numebr.setText("房间号：" + room_number);
		final TextView tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		final TextView tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		final TextView tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		final TextView tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		final TextView tv_per = (TextView) view.findViewById(R.id.tv_per);
		final TextView tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		final TextView tv_co = (TextView) view.findViewById(R.id.tv_co);
		final TextView tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		final TextView tv_press = (TextView) view.findViewById(R.id.tv_press);
		final Button btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		final Button btn_weiruzhu = (Button) view
				.findViewById(R.id.btn_weiruzhu);
		final Button btn_weidasao = (Button) view
				.findViewById(R.id.btn_weidasao);
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + room_number
						+ "(room_state)values(?)", new String[] { "2" });
				check_room_state();
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + room_number
						+ "(room_state)values(?)", new String[] { "0" });
				check_room_state();
			}
		});
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + room_number
						+ "(room_state)values(?)", new String[] { "1" });
				check_room_state();
			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_co.setText(String.valueOf(AppConfig.co));
							tv_gas.setText(String.valueOf(AppConfig.gas));
							tv_hum.setText(String.valueOf(AppConfig.hum));
							tv_ill.setText(String.valueOf(AppConfig.ill));
							tv_per.setText(String.valueOf(AppConfig.per));
							tv_pm.setText(String.valueOf(AppConfig.pm));
							tv_press.setText(String.valueOf(AppConfig.press));
							tv_smo.setText(String.valueOf(AppConfig.smo));
							tv_temp.setText(String.valueOf(AppConfig.temp));
						}
					});
				}
			}
		}).start();
		builder.setTitle("房间管理");
		builder.setPositiveButton("关闭", null);
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			if (base_state) {
				show_dialog_base("8101");
			}
			if (control_state) {
				AppConfig.room_number = "8101";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8101";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8101";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8102:
			if (base_state) {
				show_dialog_base("8102");
			}
			if (control_state) {
				AppConfig.room_number = "8102";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8102";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8102";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8103:
			if (base_state) {
				show_dialog_base("8103");
			}
			if (control_state) {
				AppConfig.room_number = "8103";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8103";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8103";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8104:
			if (base_state) {
				show_dialog_base("8104");
			}
			if (control_state) {
				AppConfig.room_number = "8104";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8104";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8104";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8201:
			if (base_state) {
				show_dialog_base("8201");
			}
			if (control_state) {
				AppConfig.room_number = "8201";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8201";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8201";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8202:
			if (base_state) {
				show_dialog_base("8202");
			}
			if (control_state) {
				AppConfig.room_number = "8202";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8202";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8202";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8203:
			if (base_state) {
				show_dialog_base("8203");
			}
			if (control_state) {
				AppConfig.room_number = "8203";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8203";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8203";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8204:
			if (base_state) {
				show_dialog_base("8204");
			}
			if (control_state) {
				AppConfig.room_number = "8204";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8204";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8204";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8301:
			if (base_state) {
				show_dialog_base("8301");
			}
			if (control_state) {
				AppConfig.room_number = "8301";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8301";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8301";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8302:
			if (base_state) {
				show_dialog_base("8302");
			}
			if (control_state) {
				AppConfig.room_number = "8302";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8302";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8302";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8303:
			if (base_state) {
				show_dialog_base("8303");
			}
			if (control_state) {
				AppConfig.room_number = "8303";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8303";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8303";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8304:
			if (base_state) {
				show_dialog_base("8304");
			}
			if (control_state) {
				AppConfig.room_number = "8304";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8304";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8304";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8404:
			if (base_state) {
				show_dialog_base("8404");
			}
			if (control_state) {
				AppConfig.room_number = "8404";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8404";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8404";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8403:
			if (base_state) {
				show_dialog_base("8403");
			}
			if (control_state) {
				AppConfig.room_number = "8403";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8403";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8403";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8401:
			if (base_state) {
				show_dialog_base("8401");
			}
			if (control_state) {
				AppConfig.room_number = "8401";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8401";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8401";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		case R.id.line_8402:
			if (base_state) {
				show_dialog_base("8402");
			}
			if (control_state) {
				AppConfig.room_number = "8402";
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (draw_state) {
				AppConfig.room_number = "8402";
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			if (link_state) {
				AppConfig.room_number = "8402";
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			break;
		}
	}

}
