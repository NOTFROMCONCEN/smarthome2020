package com.example.shengsaic20180911.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.shengsaic20180911.LoginActivity;
import com.example.shengsaic20180911.MainActivity;
import com.example.shengsaic20180911.R;
import com.example.shengsaic20180911.tools.AppConfig;
import com.example.shengsaic20180911.tools.Bean;
import com.example.shengsaic20180911.tools.DiyToast;
import com.example.shengsaic20180911.tools.ListBaseAdapter;
import com.example.shengsaic20180911.tools.MyDataBaseHelper;

public class OpFragment extends Fragment {
	private Button btn_updata_pass;
	private Button btn_del;
	private ListView lv_1;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private String get_user = "";
	private ListBaseAdapter listBaseAdapter;
	private List<Bean> list_del = new ArrayList<Bean>();
	SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		btn_del = (Button) view.findViewById(R.id.btn_del);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		initData();
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				up_pass();
			}
		});
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HashMap<Integer, Boolean> isCheck = listBaseAdapter.getMap();
				int count = listBaseAdapter.getCount();
				for (int i = 0; i < count; i++) {
					int postion = i - (count - listBaseAdapter.getCount());
					if (isCheck.get(i) != null && isCheck.get(i)) {
						isCheck.remove(i);
						listBaseAdapter.delData(postion);
						initData();
						if (!AppConfig.op) {
							logout();
						}
					}
				}
			}
		});
		return view;
	}

	private void logout() {
		// TODO Auto-generated method stub
		sharedPreferences.edit().putBoolean("autologin", false)
				.putBoolean("rember", false).putString("user", "")
				.putString("pass", "").putString("ip", "").commit();
		getActivity().finish();
	}

	private void up_pass() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.dialog_updata_pass, null, false);
		builder.setView(view);
		final EditText et_new_pass = (EditText) view
				.findViewById(R.id.et_updata_pass);
		builder.setTitle("修改密码");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (et_new_pass.getText().toString().isEmpty()) {
					up_pass();
					DiyToast.showToast(getActivity(), "请输入新密码");
				} else {
					HashMap<Integer, Boolean> isCheck = listBaseAdapter
							.getMap();
					int count = listBaseAdapter.getCount();
					for (int i = 0; i < count; i++) {
						int postion = i - (count - listBaseAdapter.getCount());
						if (isCheck.get(i) != null && isCheck.get(i)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] {
											et_new_pass.getText().toString(),
											list_del.get(postion).getUser() });
							initData();
							if (!AppConfig.op) {
								logout();
							}
						}
					}
				}
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}

	private void initData() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		List<Bean> list = new ArrayList<Bean>();
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward"))));
			}
		}
		list_del = list;
		listBaseAdapter = new ListBaseAdapter(getActivity());
		listBaseAdapter.setData(list);
		lv_1.setAdapter(listBaseAdapter);
	}
}