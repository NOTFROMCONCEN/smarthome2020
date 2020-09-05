package com.example.shengsaisaixiang20180905;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaisaixiang20180905.tools.DiyToast;
import com.example.shengsaisaixiang20180905.tools.MyDataBaseHelper;

public class RegActivity extends Activity {

	private Button btn_reg_con;
	private EditText et_euser, et_epass, et_repass;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		btn_reg_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (et_epass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else if (et_repass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_euser.getText().toString() });
					if (cursor.moveToNext()) {
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("提示").setMessage("该用户名已存在")
								.setPositiveButton("确定", null).show();
					} else {
						if (et_epass.getText().toString()
								.equals(et_repass.getText().toString())) {
							db.execSQL(
									"insert into user (username,passward,op_state)values(?,?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString(),
											"user" });
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("提示")
									.setMessage("注册成功")
									.setPositiveButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													startActivity(new Intent(
															getApplicationContext(),
															LoginActivity.class));
													finish();
												}
											}).show();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码和确认密码不一致");
						}
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_reg_con = (Button) findViewById(R.id.btn_regist_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
