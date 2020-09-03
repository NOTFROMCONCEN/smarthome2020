package com.example.shengsai2018e0903.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shengsai2018e0903.LoginActivity;
import com.example.shengsai2018e0903.R;
import com.example.shengsai2018e0903.tools.DiyToast;
import com.example.shengsai2018e0903.tools.MyDataBaseHelper;
import com.example.shengsai2018e0903.tools.UuserOp;

public class OpFragment extends Fragment {
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private ListView lv_data;
	private Button btn_del, btn_update_pass;
	private SharedPreferences sharedPreferences;
	private String get_user, get_pass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, null, false);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_data = (ListView) view.findViewById(R.id.listView1);
		btn_del = (Button) view.findViewById(R.id.btn_del);
		btn_update_pass = (Button) view.findViewById(R.id.btn_update_pass);
		lv_data.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		sharedPreferences = getActivity().getSharedPreferences("rember",
				getActivity().MODE_PRIVATE);
		getdata();
		btn_update_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				final View view = layoutInflater.inflate(R.layout.dialog_text,
						null, false);
				builder.setView(view);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								final EditText et_updata_pass_get = (EditText) view
										.findViewById(R.id.et_updata_pass_get);
								if (get_user.equals("bizideal")) {
									DiyToast.showToast(getActivity(),
											"你不能更改管路员账户");
								} else {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] {
													et_updata_pass_get
															.getText()
															.toString(),
													get_user });
									DiyToast.showToast(getActivity(), "修改成功");
									sharedPreferences.edit()
											.putBoolean("autologin", false)
											.putBoolean("rember", false)
											.putString("user", "")
											.putString("pass", "")
											.putString("ip", "").commit();
									getActivity().finish();
								}
							}
						});
				builder.setTitle("修改密码");
				builder.show();
			}
		});
		lv_data.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv_data.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				get_pass = cursor.getString(2);
				DiyToast.showToast(getActivity(), "即将进行操作的用户：" + get_user);
			}
		});
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					new AlertDialog.Builder(getActivity())
							.setTitle("提示")
							.setMessage("请选择要删除的账号信息？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					new AlertDialog.Builder(getActivity())
							.setTitle("提示")
							.setMessage("确定要删除吗？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											if (get_user.equals("bizideal")) {
												DiyToast.showToast(
														getActivity(),
														"你不能删除管理员账户");
											} else {
												db.execSQL(
														"delete from user where username = ? and passward = ?",
														new String[] {
																get_user,
																get_pass });
												getdata();
											}
										}
									}).show();
				}
			}
		});
		return view;
	}

	private void getdata() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		cursor.moveToFirst();
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				getActivity(), R.layout.activity_text, cursor, new String[] {
						"username", "passward" }, new int[] { R.id.tv_user,
						R.id.tv_pass });
		lv_data.setAdapter(simpleCursorAdapter);
	}
}
