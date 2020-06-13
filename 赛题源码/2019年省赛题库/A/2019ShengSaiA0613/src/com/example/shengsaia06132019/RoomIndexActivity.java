package com.example.shengsaia06132019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shengsaia06132019.room.RoomControl;
import com.example.shengsaia06132019.room.RoomLink;
import com.example.shengsaia06132019.room.RoomTemp;
import com.example.shengsaia06132019.tools.AppConfig;
import com.example.shengsaia06132019.tools.DiyToast;
import com.example.shengsaia06132019.tools.MySQL;
import com.example.shengsaia06132019.tools.SocketLink;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 房间管理、跳转至其他界面
 * @package_name com.example.shengsaia06132019
 * @project_name 2019ShengSaiA0613
 * @file_name RoomIndexActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class RoomIndexActivity extends Activity implements OnClickListener {

	private Button btn_base, btn_link, btn_control, btn_temp;
	private View view_base, view_link, view_control, view_temp;
	private LinearLayout line_8101;
	private LinearLayout line_8102;
	private LinearLayout line_8103;
	private LinearLayout line_8104;
	private LinearLayout line_8201;
	private LinearLayout line_8202;
	private LinearLayout line_8203;
	private LinearLayout line_8204;
	private LinearLayout line_8301;
	private LinearLayout line_8302;
	private LinearLayout line_8303;
	private LinearLayout line_8304;
	private LinearLayout line_8401;
	private LinearLayout line_8402;
	private LinearLayout line_8403;
	private LinearLayout line_8404;
	private boolean room_base = true;
	private boolean room_link = false;
	private boolean room_control = false;
	private boolean room_temp = false;
	private MySQL mySQL;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_index);
		setTitle("前台管理");
		initView();
		SocketLink.start_server_link(RoomIndexActivity.this);// 开启网络连接、数据采集
		check_room();// 检查房间状态
		// 房间管理
		btn_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.VISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_base = true;
				room_control = false;
				room_link = false;
				room_temp = false;
			}
		});
		// 房间控制
		btn_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.VISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_base = false;
				room_control = true;
				room_link = false;
				room_temp = false;
			}
		});
		// 联动管理
		btn_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.VISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_base = false;
				room_control = false;
				room_link = true;
				room_temp = false;
			}
		});
		// 温度趋势
		btn_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.VISIBLE);
				room_base = false;
				room_control = false;
				room_link = false;
				room_temp = true;
			}
		});
	}

	private void check_room() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				Cursor cursor = db.rawQuery(
						"select * from room_8" + String.valueOf(i) + "0"
								+ String.valueOf(j), null);
				if (cursor.getCount() != 0) {
					cursor.moveToLast();
					String string = cursor.getString(cursor
							.getColumnIndex("room_state"));
					if (i == 1) {
						if (j == 1) {
							// 未入住
							if (string.equals("1")) {
								line_8101.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8101.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8101.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							// 未入住
							if (string.equals("1")) {
								line_8102.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8102.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8102.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							// 未入住
							if (string.equals("1")) {
								line_8103.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8103.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8103.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							// 未入住
							if (string.equals("1")) {
								line_8104.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8104.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8104.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 2) {
						if (j == 1) {
							// 未入住
							if (string.equals("1")) {
								line_8201.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8201.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8201.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							// 未入住
							if (string.equals("1")) {
								line_8202.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8202.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8202.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							// 未入住
							if (string.equals("1")) {
								line_8203.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8203.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8203.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							// 未入住
							if (string.equals("1")) {
								line_8204.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8204.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8204.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 3) {
						if (j == 1) {
							// 未入住
							if (string.equals("1")) {
								line_8301.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8301.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8301.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							// 未入住
							if (string.equals("1")) {
								line_8302.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8302.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8302.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							// 未入住
							if (string.equals("1")) {
								line_8303.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8303.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8303.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							// 未入住
							if (string.equals("1")) {
								line_8304.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8304.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8304.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 4) {
						if (j == 1) {
							// 未入住
							if (string.equals("1")) {
								line_8401.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8401.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8401.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							// 未入住
							if (string.equals("1")) {
								line_8402.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8402.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8402.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							// 未入住
							if (string.equals("1")) {
								line_8403.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8403.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8403.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							// 未入住
							if (string.equals("1")) {
								line_8404.setBackgroundColor(Color.GREEN);
							}
							// 未打扫
							if (string.equals("2")) {
								line_8404.setBackgroundColor(Color.GRAY);
							}
							// 已入住
							if (string.equals("3")) {
								line_8404.setBackgroundColor(Color.RED);
							}
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "数据库错误");
				}
			}
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
		btn_base = (Button) findViewById(R.id.btn_base);
		btn_control = (Button) findViewById(R.id.btn_control);
		btn_link = (Button) findViewById(R.id.btn_link);
		btn_temp = (Button) findViewById(R.id.btn_temp);
		view_base = (View) findViewById(R.id.view_base);
		view_control = (View) findViewById(R.id.view_control);
		view_link = (View) findViewById(R.id.view_link);
		view_temp = (View) findViewById(R.id.view_temp);
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
	}

	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				RoomIndexActivity.this);
		builder.setTitle("房间管理");
		View view = LayoutInflater.from(RoomIndexActivity.this).inflate(
				R.layout.layout_dialog_base, null, false);
		builder.setView(view);
		final Button btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		final Button btn_weiruzhu = (Button) view
				.findViewById(R.id.btn_weiruzhu);
		final Button btn_weidasao = (Button) view
				.findViewById(R.id.btn_weidasao);
		final TextView tv_base_roomnumber = (TextView) view
				.findViewById(R.id.tv_base_roomnumber);
		tv_base_roomnumber.setText("房间号：" + AppConfig.roomnumber);
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.roomnumber
						+ "(room_state)values(2)");
				check_room();
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.roomnumber
						+ "(room_state)values(1)");
				check_room();
			}
		});
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.roomnumber
						+ "(room_state)values(3)");
				check_room();
			}
		});

		final TextView tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		final TextView tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		final TextView tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		final TextView tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		final TextView tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		final TextView tv_press = (TextView) view.findViewById(R.id.tv_press);
		final TextView tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		final TextView tv_co = (TextView) view.findViewById(R.id.tv_co);
		final TextView tv_per = (TextView) view.findViewById(R.id.tv_per);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
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
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();

		builder.setPositiveButton("关闭", null);
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			AppConfig.roomnumber = "8101";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8102:
			AppConfig.roomnumber = "8102";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8103:
			AppConfig.roomnumber = "8103";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8104:
			AppConfig.roomnumber = "8104";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8201:
			AppConfig.roomnumber = "8201";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8202:
			AppConfig.roomnumber = "8202";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8203:
			AppConfig.roomnumber = "8203";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8204:
			AppConfig.roomnumber = "8204";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8301:
			AppConfig.roomnumber = "8301";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8302:
			AppConfig.roomnumber = "8302";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8303:
			AppConfig.roomnumber = "8303";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8304:
			AppConfig.roomnumber = "8304";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8401:
			AppConfig.roomnumber = "8401";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8402:
			AppConfig.roomnumber = "8402";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		case R.id.line_8403:
			AppConfig.roomnumber = "8403";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;

		case R.id.line_8404:
			AppConfig.roomnumber = "8404";
			if (room_base) {
				showDialog();
			}
			if (room_control) {
				startActivity(new Intent(getApplicationContext(),
						RoomControl.class));
			}
			if (room_link) {
				startActivity(new Intent(getApplicationContext(),
						RoomLink.class));
			}
			if (room_temp) {
				startActivity(new Intent(getApplicationContext(),
						RoomTemp.class));
			}
			break;
		default:
			break;
		}
	}
}
