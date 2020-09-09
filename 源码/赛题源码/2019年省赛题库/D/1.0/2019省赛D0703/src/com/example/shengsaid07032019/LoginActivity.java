package com.example.shengsaid07032019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.shengsaid07032019.fragment.BarActivity;
import com.example.shengsaid07032019.tools.AppTools;
import com.example.shengsaid07032019.tools.DiyToast;
import com.example.shengsaid07032019.tools.MySQLiteHelper;
import com.example.shengsaid07032019.tools.TextChanger;

public class LoginActivity extends Activity {

	private LinearLayout line_login, line_reg, line_update_pass;
	private Button btn_login, btn_reg, btn_update_pass, btn_exit, btn_con,
			btn_cls, btn_update_con, btn_update_cls;
	private EditText et_user, et_pass, et_ip, et_reg_pass, et_reg_repass,
			et_reg_user, et_update_user, et_old_pass, et_new_pass;
	private SharedPreferences sharedPreferences;
	private MySQLiteHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_user.setText(sharedPreferences.getString("user", null));
		et_ip.setText(sharedPreferences.getString("ip", null));
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.INVISIBLE);
				line_reg.setVisibility(View.VISIBLE);
				line_update_pass.setVisibility(View.INVISIBLE);
			}
		});
		btn_update_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.INVISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_update_pass.setVisibility(View.VISIBLE);
			}
		});
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_update_pass.setVisibility(View.INVISIBLE);
			}
		});
		btn_update_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_update_pass.setVisibility(View.INVISIBLE);
			}
		});
		btn_update_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_update_user.getText().toString().isEmpty()
						|| et_old_pass.getText().toString().isEmpty()
						|| et_new_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�����пհ���");
				} else {
					if (et_new_pass.getText().toString()
							.equals(et_old_pass.getText().toString())) {
						DiyToast.showToast(getApplicationContext(), "�¾����벻��һ��");
					} else {
						Cursor cursor = db.rawQuery(
								"select * from user where username = ?",
								new String[] { et_update_user.getText()
										.toString() });
						if (cursor.moveToNext()) {
							String sql_old_pass = cursor.getString(cursor
									.getColumnIndex("passward"));
							if (et_old_pass.getText().toString()
									.equals(sql_old_pass)) {
								db.execSQL(
										"update user set passward = ? where username = ?",
										new String[] {
												et_new_pass.getText()
														.toString(),
												et_update_user.getText()
														.toString() });
								new AlertDialog.Builder(LoginActivity.this)
										.setTitle("�޸ĳɹ�").setMessage("�����޸ĳɹ�")
										.setPositiveButton("Ok", null).show();
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.INVISIBLE);
								line_update_pass.setVisibility(View.INVISIBLE);
							} else {
								new AlertDialog.Builder(LoginActivity.this)
										.setTitle("�޸�ʧ��").setMessage("���������")
										.setPositiveButton("Ok", null).show();
							}
						} else {
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("�޸�ʧ��")
									.setMessage("�޸�ʧ�ܣ�������û���������")
									.setPositiveButton("Ok", null).show();
						}
					}
				}
			}
		});
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_reg_pass.getText().toString().isEmpty()
						|| et_reg_repass.getText().toString().isEmpty()
						|| et_reg_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�����пհ���");
				} else {
					if (et_reg_pass.getText().toString()
							.equals(et_reg_repass.getText().toString())) {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ?",
										new String[] { et_reg_user.getText()
												.toString() });
						if (cursor.moveToNext()) {
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("ע��ʧ��")
									.setMessage("�û����Ѵ��ڣ�������ע��")
									.setPositiveButton("Ok", null).show();
						} else {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] {
											et_reg_user.getText().toString(),
											et_reg_pass.getText().toString() });
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("ע��ɹ�").setMessage("�û�ע��ɹ�")
									.setPositiveButton("Ok", null).show();
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.INVISIBLE);
							line_update_pass.setVisibility(View.INVISIBLE);
						}
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ʧ��").setMessage("�ظ����벻һ��")
								.setPositiveButton("Ok", null).show();
					}
				}
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�����пհ���");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						sharedPreferences
								.edit()
								.putString("user", et_user.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("ip", et_ip.getText().toString())
								.commit();
						DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
						AppTools.IP = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("��¼ʧ��").setMessage("�û������������")
								.setPositiveButton("Ok", null).show();
					}
				}
			}
		});
		et_pass.setTransformationMethod(new TextChanger());
	}

	private void initView() {
		// TODO Auto-generated method stub
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_new_pass = (EditText) findViewById(R.id.et_new_pass);
		et_old_pass = (EditText) findViewById(R.id.et_old_pass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_update_user = (EditText) findViewById(R.id.et_update_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_update_pass = (LinearLayout) findViewById(R.id.line_update_pass);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_update_pass = (Button) findViewById(R.id.btn_update_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_update_cls = (Button) findViewById(R.id.btn_update_cls);
		btn_update_con = (Button) findViewById(R.id.btn_update_con);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_update_pass.setVisibility(View.INVISIBLE);
		dbHelper = new MySQLiteHelper(getApplicationContext(), "info.db", null,
				2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}
}
