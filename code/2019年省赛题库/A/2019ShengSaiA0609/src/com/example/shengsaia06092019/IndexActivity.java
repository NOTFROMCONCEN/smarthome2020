package com.example.shengsaia06092019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndexActivity extends Activity implements OnClickListener {

	private Button btn_base, btn_control, btn_link, btn_temp;
	private MySQL mySQL;
	private SQLiteDatabase db;
	private View view_base, view_control, view_link, view_temp;
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
	private String room_state = "base";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		setTitle("前台管理");
		initView();
		SocketLink.web_server(IndexActivity.this);
		view_base.setVisibility(View.VISIBLE);
		view_control.setVisibility(View.INVISIBLE);
		view_link.setVisibility(View.INVISIBLE);
		view_temp.setVisibility(View.INVISIBLE);
		btn_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.VISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_state = "base";
			}
		});
		btn_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.VISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_state = "control";
			}
		});
		btn_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.VISIBLE);
				view_temp.setVisibility(View.INVISIBLE);
				room_state = "link";
			}
		});
		btn_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_base.setVisibility(View.INVISIBLE);
				view_control.setVisibility(View.INVISIBLE);
				view_link.setVisibility(View.INVISIBLE);
				view_temp.setVisibility(View.VISIBLE);
				room_state = "temp";
			}
		});
		check_room_state();
	}

	private void check_room_state() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				Cursor cursor = db.rawQuery(
						"select * from room8" + String.valueOf(i) + "0"
								+ String.valueOf(j), null);
				if (cursor.getCount() != 0) {
					cursor.moveToLast();
					String string = cursor.getString(cursor
							.getColumnIndex("room_state"));
					if (i == 4) {
						if (j == 1) {
							if (string.equals("1")) {
								line_8401.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8401.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8401.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							if (string.equals("1")) {
								line_8402.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8402.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8402.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							if (string.equals("1")) {
								line_8403.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8403.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8403.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							if (string.equals("1")) {
								line_8404.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8404.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8404.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 3) {
						if (j == 1) {
							if (string.equals("1")) {
								line_8301.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8301.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8301.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							if (string.equals("1")) {
								line_8302.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8302.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8302.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							if (string.equals("1")) {
								line_8303.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8303.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8303.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							if (string.equals("1")) {
								line_8304.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8304.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8304.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 2) {
						if (j == 1) {
							if (string.equals("1")) {
								line_8201.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8201.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8201.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							if (string.equals("1")) {
								line_8202.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8202.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8202.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							if (string.equals("1")) {
								line_8203.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8203.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8203.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							if (string.equals("1")) {
								line_8204.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8204.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8204.setBackgroundColor(Color.RED);
							}
						}
					}
					if (i == 1) {
						if (j == 1) {
							if (string.equals("1")) {
								line_8101.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8101.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8101.setBackgroundColor(Color.RED);
							}
						}
						if (j == 2) {
							if (string.equals("1")) {
								line_8102.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8102.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8102.setBackgroundColor(Color.RED);
							}
						}
						if (j == 3) {
							if (string.equals("1")) {
								line_8103.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8103.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8103.setBackgroundColor(Color.RED);
							}
						}
						if (j == 4) {
							if (string.equals("1")) {
								line_8104.setBackgroundColor(Color.GREEN);
							}
							if (string.equals("2")) {
								line_8104.setBackgroundColor(Color.DKGRAY);
							}
							if (string.equals("3")) {
								line_8104.setBackgroundColor(Color.RED);
							}
						}
					}
				}
			}
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
		view_base = (View) findViewById(R.id.view_base);
		view_control = (View) findViewById(R.id.view_control);
		view_link = (View) findViewById(R.id.view_link);
		view_temp = (View) findViewById(R.id.view_temp);
		btn_base = (Button) findViewById(R.id.btn_base);
		btn_control = (Button) findViewById(R.id.btn_control);
		btn_link = (Button) findViewById(R.id.btn_link);
		btn_temp = (Button) findViewById(R.id.btn_temp);
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
				IndexActivity.this);
		View view = LayoutInflater.from(IndexActivity.this).inflate(
				R.layout.layout_base_dialog, null, false);
		builder.setView(view);
		final TextView tv_room_number = (TextView) view
				.findViewById(R.id.tv_base_roomnumber);
		tv_room_number.setText(AppConfig.room_number);
		Button btn_weiruzhu = (Button) view.findViewById(R.id.btn_weiruzhu);
		Button btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		Button btn_weidasao = (Button) view.findViewById(R.id.btn_weidasao);
		final TextView tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		final TextView tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		final TextView tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		final TextView tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		final TextView tv_press = (TextView) view.findViewById(R.id.tv_press);
		final TextView tv_per = (TextView) view.findViewById(R.id.tv_per);
		final TextView tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		final TextView tv_co = (TextView) view.findViewById(R.id.tv_co);
		final TextView tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_co.setText(String.valueOf(AppConfig.co2));
							tv_gas.setText(String.valueOf(AppConfig.gas));
							tv_hum.setText(String.valueOf(AppConfig.hum));
							tv_ill.setText(String.valueOf(AppConfig.ill));
							tv_pm.setText(String.valueOf(AppConfig.pm));
							tv_press.setText(String.valueOf(AppConfig.press));
							tv_smo.setText(String.valueOf(AppConfig.smo));
							tv_temp.setText(String.valueOf(AppConfig.temp));
							if (AppConfig.per != 1) {
								tv_per.setText("无人");
							} else {
								tv_per.setText("有人");
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
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room"
						+ tv_room_number.getText().toString()
						+ "(room_state)values(?)", new String[] { "2" });
				check_room_state();
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room"
						+ tv_room_number.getText().toString()
						+ "(room_state)values(?)", new String[] { "1" });
				check_room_state();
			}
		});
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room"
						+ tv_room_number.getText().toString()
						+ "(room_state)values(?)", new String[] { "3" });
				check_room_state();
			}
		});
		builder.setTitle("房间管理");
		builder.setPositiveButton("关闭", null);
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.line_8304:
			AppConfig.room_number = "8304";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8303:
			AppConfig.room_number = "8303";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8302:
			AppConfig.room_number = "8302";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8301:
			AppConfig.room_number = "8301";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;

		case R.id.line_8404:
			AppConfig.room_number = "8404";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8403:
			AppConfig.room_number = "8403";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8402:
			AppConfig.room_number = "8402";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8401:
			AppConfig.room_number = "8401";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;

		case R.id.line_8204:
			AppConfig.room_number = "8204";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8203:
			AppConfig.room_number = "8203";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8202:
			AppConfig.room_number = "8202";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8201:
			AppConfig.room_number = "8201";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;

		case R.id.line_8104:
			AppConfig.room_number = "8104";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8103:
			AppConfig.room_number = "8103";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8102:
			AppConfig.room_number = "8102";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		case R.id.line_8101:
			AppConfig.room_number = "8101";
			if (room_state.equals("base")) {
				showDialog();
			}
			if (room_state.equals("control")) {
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
			if (room_state.equals("link")) {
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
			if (room_state.equals("temp")) {
				startActivity(new Intent(getApplicationContext(),
						DrawActivity.class));
			}
			break;
		default:
			break;
		}
	}
}
