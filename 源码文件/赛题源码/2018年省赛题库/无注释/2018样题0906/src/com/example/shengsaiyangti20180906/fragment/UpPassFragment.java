package com.example.shengsaiyangti20180906.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaiyangti20180906.MainActivity;
import com.example.shengsaiyangti20180906.R;
import com.example.shengsaiyangti20180906.tools.DiyToast;
import com.example.shengsaiyangti20180906.tools.MyDataBaseHelper;

public class UpPassFragment extends Fragment {

	private Button btn_update_con;
	private EditText et_update_user;
	private EditText et_update_newpass;
	private EditText et_update_oldpass;
	private EditText et_update_repass;

	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.activity_updata_pass, null, false);
		initView(view);
		btn_update_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_update_newpass.getText().toString().isEmpty()
						|| et_update_oldpass.getText().toString().isEmpty()
						|| et_update_repass.getText().toString().isEmpty()
						|| et_update_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "不能有空白项");
				} else {
					Cursor cursor = db
							.rawQuery("select * from user where username = ?",
									new String[] { et_update_user.getText()
											.toString() });
					if (cursor.moveToNext()) {
						if (cursor.getString(cursor.getColumnIndex("passward"))
								.toString()
								.equals(et_update_oldpass.getText().toString())) {
							if (et_update_newpass
									.getText()
									.toString()
									.equals(et_update_oldpass.getText()
											.toString())) {
								DiyToast.showToast(getActivity(), "新旧密码不能一致");
							} else {
								if (et_update_newpass
										.getText()
										.toString()
										.equals(et_update_repass.getText()
												.toString())) {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] {
													et_update_newpass.getText()
															.toString(),
													et_update_user.getText()
															.toString() });
									DiyToast.showToast(getActivity(), "修改成功");
									startActivity(new Intent(getActivity(),
											MainActivity.class));
									getActivity().finish();
								} else {
									DiyToast.showToast(getActivity(),
											"新密码和确认密码不一致");
								}
							}
						} else {
							DiyToast.showToast(getActivity(), "旧密码错误");
						}
					} else {
						DiyToast.showToast(getActivity(), "用户名不存在");
					}
				}
			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_update_con = (Button) view.findViewById(R.id.btn_updata_con);
		et_update_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		et_update_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		et_update_repass = (EditText) view.findViewById(R.id.et_updata_repass);
		et_update_user = (EditText) view.findViewById(R.id.et_updata_user);
	}
}
