package com.example.shengsai2019a0914;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.shengsai2019a0914.tools.AppConfig;
import com.example.shengsai2019a0914.tools.MyDataBaseHelper;

public class IndexActivity extends Activity implements OnClickListener {

	private LinearLayout ll_8101, ll_8102, ll_8103, ll_8104, ll_8201, ll_8202,
			ll_8203, ll_8204, ll_8301, ll_8302, ll_8303, ll_8304, ll_8401,
			ll_8402, ll_8403, ll_8404;
	private Button btn_room_base, btn_room_control, btn_room_link,
			btn_room_temp;
	private View view_base, view_control, view_link, view_temp;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		setTitle("前台管理");
		initView();
		get_room();
		AppConfig.web_start(IndexActivity.this);
		AppConfig.now_state = "base";
		btn_room_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppConfig.now_state = "base";
				view_base.setVisibility(View.VISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
			}
		});
		btn_room_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppConfig.now_state = "control";
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.VISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
			}
		});
		btn_room_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppConfig.now_state = "link";
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.VISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
			}
		});
		btn_room_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppConfig.now_state = "temp";
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.VISIBLE);
			}
		});
	}

	private void get_room() {
		// TODO Auto-generated method stub
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (i == 1) {
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8101.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8101.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8101.setBackgroundColor(Color.RED);
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8102.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8102.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8102.setBackgroundColor(Color.RED);
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8103.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8103.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8103.setBackgroundColor(Color.RED);
						}
					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8104.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8104.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8104.setBackgroundColor(Color.RED);
						}
					}
				}
				if (i == 2) {
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8201.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8201.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8201.setBackgroundColor(Color.RED);
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8202.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8202.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8202.setBackgroundColor(Color.RED);
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8203.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8203.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8203.setBackgroundColor(Color.RED);
						}
					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8204.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8204.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8204.setBackgroundColor(Color.RED);
						}
					}
				}
				if (i == 3) {
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8301.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8301.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8301.setBackgroundColor(Color.RED);
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8302.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8302.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8302.setBackgroundColor(Color.RED);
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8303.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8303.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8303.setBackgroundColor(Color.RED);
						}
					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8304.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8304.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8304.setBackgroundColor(Color.RED);
						}
					}
				}
				if (i == 4) {

					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8401.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8401.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8401.setBackgroundColor(Color.RED);
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8402.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8402.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8402.setBackgroundColor(Color.RED);
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8403.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8403.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8403.setBackgroundColor(Color.RED);
						}
					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room_8"
								+ String.valueOf(i) + "0" + String.valueOf(j),
								null);
						cursor.moveToLast();
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("1")) {
							ll_8404.setBackgroundColor(Color.GREEN);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("2")) {
							ll_8404.setBackgroundColor(Color.GRAY);
						}
						if (cursor.getString(
								cursor.getColumnIndex("room_state"))
								.equals("3")) {
							ll_8404.setBackgroundColor(Color.RED);
						}
					}

				}
			}
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		ll_8101 = (LinearLayout) findViewById(R.id.ll_8101);
		ll_8102 = (LinearLayout) findViewById(R.id.ll_8102);
		ll_8103 = (LinearLayout) findViewById(R.id.ll_8103);
		ll_8104 = (LinearLayout) findViewById(R.id.ll_8104);
		ll_8201 = (LinearLayout) findViewById(R.id.ll_8201);
		ll_8202 = (LinearLayout) findViewById(R.id.ll_8202);
		ll_8203 = (LinearLayout) findViewById(R.id.ll_8203);
		ll_8204 = (LinearLayout) findViewById(R.id.ll_8204);
		ll_8301 = (LinearLayout) findViewById(R.id.ll_8301);
		ll_8302 = (LinearLayout) findViewById(R.id.ll_8302);
		ll_8303 = (LinearLayout) findViewById(R.id.ll_8303);
		ll_8304 = (LinearLayout) findViewById(R.id.ll_8304);
		ll_8401 = (LinearLayout) findViewById(R.id.ll_8401);
		ll_8402 = (LinearLayout) findViewById(R.id.ll_8402);
		ll_8403 = (LinearLayout) findViewById(R.id.ll_8403);
		ll_8404 = (LinearLayout) findViewById(R.id.ll_8404);
		btn_room_base = (Button) findViewById(R.id.btn_room_base);
		btn_room_control = (Button) findViewById(R.id.btn_room_control);
		btn_room_link = (Button) findViewById(R.id.btn_room_link);
		btn_room_temp = (Button) findViewById(R.id.btn_room_temp);
		view_base = (View) findViewById(R.id.view_base);
		view_control = (View) findViewById(R.id.view_control);
		view_link = (View) findViewById(R.id.view_link);
		view_temp = (View) findViewById(R.id.view_temp);
		view_base.setVisibility(View.VISIBLE);
		view_control.setVisibility(View.INVISIBLE);
		view_link.setVisibility(View.INVISIBLE);
		view_temp.setVisibility(View.INVISIBLE);
		ll_8101.setOnClickListener(this);
		ll_8102.setOnClickListener(this);
		ll_8103.setOnClickListener(this);
		ll_8104.setOnClickListener(this);
		ll_8201.setOnClickListener(this);
		ll_8202.setOnClickListener(this);
		ll_8203.setOnClickListener(this);
		ll_8204.setOnClickListener(this);
		ll_8301.setOnClickListener(this);
		ll_8302.setOnClickListener(this);
		ll_8303.setOnClickListener(this);
		ll_8304.setOnClickListener(this);
		ll_8401.setOnClickListener(this);
		ll_8402.setOnClickListener(this);
		ll_8403.setOnClickListener(this);
		ll_8404.setOnClickListener(this);
	}

	private void show_dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(
				IndexActivity.this);
		builder.setTitle("房间管理");
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.dialog_base_show, null, false);
		builder.setView(view);
		final TextView tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		final TextView tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		final TextView tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		final TextView tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		final TextView tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		final TextView tv_per = (TextView) view.findViewById(R.id.tv_per);
		final TextView tv_co = (TextView) view.findViewById(R.id.tv_co);
		final TextView tv_press = (TextView) view.findViewById(R.id.tv_press);
		final TextView tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		final Button btn_c, btn_in, btn_out;
		btn_c = (Button) view.findViewById(R.id.btn_room_c);
		btn_in = (Button) view.findViewById(R.id.btn_room_in);
		btn_out = (Button) view.findViewById(R.id.btn_room_out);
		final TextView tv_number = (TextView) view
				.findViewById(R.id.tv_dialog_number);
		tv_number.setText("房间号：" + AppConfig.room_number);
		btn_c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.room_number
						+ " (room_state)values(?)", new String[] { "2" });
				get_room();
			}
		});
		btn_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.room_number
						+ " (room_state)values(?)", new String[] { "3" });
				get_room();
			}
		});
		btn_out.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room_" + AppConfig.room_number
						+ " (room_state)values(?)", new String[] { "1" });
				get_room();
			}
		});

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
							tv_temp.setText(String.valueOf(AppConfig.temp));
							tv_pm.setText(String.valueOf(AppConfig.pm));
							tv_press.setText(String.valueOf(AppConfig.press));
							tv_smo.setText(String.valueOf(AppConfig.smo));
							if (AppConfig.per == 1) {
								tv_per.setText("有人");
							} else {
								tv_per.setText("无人");
							}
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
		builder.setPositiveButton("确定", null);
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_8101:
			AppConfig.room_number = "8101";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8102:
			AppConfig.room_number = "8102";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8103:
			AppConfig.room_number = "8103";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8104:
			AppConfig.room_number = "8104";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;

		case R.id.ll_8201:
			AppConfig.room_number = "8201";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8202:
			AppConfig.room_number = "8202";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8203:
			AppConfig.room_number = "8203";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8204:
			AppConfig.room_number = "8204";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;

		case R.id.ll_8301:
			AppConfig.room_number = "8301";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8302:
			AppConfig.room_number = "8302";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8303:
			AppConfig.room_number = "8303";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8304:
			AppConfig.room_number = "8304";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;

		case R.id.ll_8401:
			AppConfig.room_number = "8401";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8402:
			AppConfig.room_number = "8402";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8403:
			AppConfig.room_number = "8403";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;
		case R.id.ll_8404:
			AppConfig.room_number = "8404";
			if (AppConfig.now_state.equals("base")) {
				show_dialog();
			}
			if (AppConfig.now_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (AppConfig.now_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (AppConfig.now_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						TempActivity.class));
			}
			break;

		default:
			break;
		}
	}
}
