package com.example.shengsai2018d0910;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsai2018d0910.toosl.DiyToast;
import com.example.shengsai2018d0910.toosl.MyDataBaseHelper;

public class RegActivity extends Activity implements
		android.view.View.OnClickListener {
	private Button btn_con;
	private Button btn_cls;
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	boolean isTrue = false;
	Pattern pattern;
	Matcher matcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			finish();
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();
			epass = et_epass.getText().toString();
			repass = et_repass.getText().toString();
			if (euser.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (epass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else if (repass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						EditTextChanger();
						if (isTrue) {
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"注册密码必须是字母和数字格式");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showToast(getApplicationContext(),
										"注册完成");
								finish();
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码不足6位");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "验证密码不一致");
					}
				}
			}
			break;

		default:
			break;
		}
	}

	private void EditTextChanger() {
		// TODO Auto-generated method stub
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 4) {
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}
}
