package com.example.shengsai2018a0913.fragment;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shengsai2018a0913.R;
import com.example.shengsai2018a0913.tools.Bean;
import com.example.shengsai2018a0913.tools.ListHelpAdapter;
import com.example.shengsai2018a0913.tools.MyDataBaseHelper;

public class UserActivity extends Fragment {

	private ListView listView;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private List<Bean> list = new ArrayList<Bean>();
	private ListHelpAdapter listHelpAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_user, null, false);
		initView(view);
		initData();
		return view;
	}

	private void initData() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward")), cursor.getString(cursor
						.getColumnIndex("op_state"))));
			}
		}
		listHelpAdapter.setData(list);
		listView.setAdapter(listHelpAdapter);
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		listView = (ListView) view.findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		listHelpAdapter = new ListHelpAdapter(getActivity());
	}

}
