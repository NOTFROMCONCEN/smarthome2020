package com.example.shengsai2019d0920;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2019d0920.fragment.BarActivity;
import com.example.shengsai2019d0920.tools.DiyToast;
import com.example.shengsai2019d0920.tools.TextChanger;

public class LoginActivity extends Activity {

	private LinearLayout ll_login;
	private LinearLayout ll_reg;
	private LinearLayout ll_up;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private SharedPreferences sharedPreferences;
	private EditText et_user, et_pass, et_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_user.setText(sharedPreferences.getString("user", null));
		et_ip.setText(sharedPreferences.getString("ip", null));
		et_pass.setTransformationMethod(new TextChanger());
	}

	private void initView() {
		// TODO Auto-generated method stub
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		ll_login = (LinearLayout) findViewById(R.id.ll_login);
		ll_reg = (LinearLayout) findViewById(R.id.ll_reg);
		ll_up = (LinearLayout) findViewById(R.id.ll_up);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}

	public void exit(View view) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	public void login(View view) {
		// TODO Auto-generated method stub
		String user = ((EditText) findViewById(R.id.et_user)).getText()
				.toString();
		String pass = ((EditText) findViewById(R.id.et_pass)).getText()
				.toString();
		String ip = ((EditText) findViewById(R.id.et_ip)).getText().toString();
		if (user.isEmpty() || pass.isEmpty() || ip.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "�����пհ���");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (cursor.moveToNext()) {
				sharedPreferences.edit().putString("user", user)
						.putString("pass", pass).putString("ip", ip).commit();
				SocketClient.ip = ip;
				startActivity(new Intent(getApplicationContext(),
						BarActivity.class));
				finish();
			} else {
				new AlertDialog.Builder(LoginActivity.this).setTitle("��¼ʧ��")
						.setMessage("�û������������").setPositiveButton("ok", null)
						.show();
			}
		}
	}

	public void reg_con(View view) {
		// TODO Auto-generated method stub
		String user = ((EditText) findViewById(R.id.et_reg_user)).getText()
				.toString();
		String pass = ((EditText) findViewById(R.id.et_reg_pass)).getText()
				.toString();
		String repass = ((EditText) findViewById(R.id.et_reg_repass)).getText()
				.toString();
		if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "�����пհ���");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (!cursor.moveToNext()) {
				if (pass.equals(repass)) {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { user, pass });
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("ע��ɹ�")
							.setMessage("�û�ע��ɹ�")
							.setPositiveButton("ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											ll_login.setVisibility(View.VISIBLE);
											ll_reg.setVisibility(View.GONE);
											ll_up.setVisibility(View.GONE);
										}
									}).show();
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("ע��ʧ��").setMessage("�ظ����벻һ��")
							.setPositiveButton("ok", null).show();
				}
			} else {
				new AlertDialog.Builder(LoginActivity.this).setTitle("ע��ʧ��")
						.setMessage("�û����Ѵ��ڣ�������ע��")
						.setPositiveButton("ok", null).show();
			}
		}
	}

	public void reg_cls(View view) {
		// TODO Auto-generated method stub
		ll_login.setVisibility(View.VISIBLE);
		ll_reg.setVisibility(View.GONE);
		ll_up.setVisibility(View.GONE);
	}

	public void reg(View view) {
		// TODO Auto-generated method stub
		ll_login.setVisibility(View.GONE);
		ll_reg.setVisibility(View.VISIBLE);
		ll_up.setVisibility(View.GONE);
	}

	public void up(View view) {
		// TODO Auto-generated method stub
		ll_login.setVisibility(View.GONE);
		ll_reg.setVisibility(View.GONE);
		ll_up.setVisibility(View.VISIBLE);
	}

	public void up_con(View view) {
		// TODO Auto-generated method stub
		String user = ((EditText) findViewById(R.id.et_up_user)).getText()
				.toString();
		String oldpass = ((EditText) findViewById(R.id.et_up_oldpass))
				.getText().toString();
		String newpass = ((EditText) findViewById(R.id.et_up_newpass))
				.getText().toString();
		if (user.isEmpty() || oldpass.isEmpty() || newpass.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "�����пհ���");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? ",
					new String[] { user });
			if (cursor.moveToNext()) {
				String get_oldpassString = cursor.getString(cursor
						.getColumnIndex("passward"));
				if (get_oldpassString.equals(oldpass)) {
					if (!newpass.equals(oldpass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { newpass, user });
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸ĳɹ�")
								.setMessage("�����޸ĳɹ�")
								.setPositiveButton("ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												ll_login.setVisibility(View.VISIBLE);
												ll_reg.setVisibility(View.GONE);
												ll_up.setVisibility(View.GONE);
											}
										}).show();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸�ʧ��").setMessage("�¾����벻��һ��")
								.setPositiveButton("ok", null).show();
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("�޸�ʧ��").setMessage("���������")
							.setPositiveButton("ok", null).show();
				}
			} else {
				new AlertDialog.Builder(LoginActivity.this).setTitle("�޸�ʧ��")
						.setMessage("�޸�ʧ�ܣ�������û���������")
						.setPositiveButton("ok", null).show();
			}
		}
	}

	public void up_cls(View view) {
		// TODO Auto-generated method stub
		ll_login.setVisibility(View.VISIBLE);
		ll_reg.setVisibility(View.GONE);
		ll_up.setVisibility(View.GONE);
	}
}
