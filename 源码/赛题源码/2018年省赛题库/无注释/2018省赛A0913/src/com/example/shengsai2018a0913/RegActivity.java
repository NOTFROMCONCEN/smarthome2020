package com.example.shengsai2018a0913;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsai2018a0913.tools.DiyToast;
import com.example.shengsai2018a0913.tools.MyDataBaseHelper;

public class RegActivity extends Activity {

	private Button btn_con;
	private EditText et_euser, et_epass, et_repass;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (et_epass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "����������");
				} else if (et_repass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������ظ�����");
				} else {
					if (et_epass.getText().toString()
							.equals(et_repass.getText().toString())) {

						Cursor cursor = db.rawQuery(
								"select * from user where username = ?",
								new String[] { et_euser.getText().toString() });
						if (cursor.moveToNext()) {
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("��ʾ").setMessage("���û��Ѵ���")
									.setPositiveButton("ȷ��", null).show();
						} else {
							db.execSQL(
									"insert into user (username,passward,op_state)values(?,?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString(),
											"user" });
							DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
							finish();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"������ظ����벻һ��");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
