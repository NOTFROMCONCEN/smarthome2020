package com.example.shengsaic20180911;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaic20180911.tools.DiyToast;
import com.example.shengsaic20180911.tools.MyDataBaseHelper;
import com.example.shengsaic20180911.tools.SQLHelper;

public class RegActivity extends Activity {

	private Button btn_reg;
	private EditText et_euser, et_epass, et_repass;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else {
					if (SQLHelper.raw(getApplicationContext(), et_euser
							.getText().toString(), et_epass.getText()
							.toString())) {
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("提示").setMessage("用户名已存在")
								.setPositiveButton("确定", null).show();
					} else {
						if (et_epass.getText().toString()
								.equals(et_repass.getText().toString())) {
							SQLHelper.insert(getApplicationContext(), et_euser
									.getText().toString(), et_epass.getText()
									.toString());
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
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
