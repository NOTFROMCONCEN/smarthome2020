package com.example.shengsai2018b0912;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.shengsai2018b0912.tools.Bean;
import com.example.shengsai2018b0912.tools.ListHelpAdapter;
import com.example.shengsai2018b0912.tools.MyDataBaseHelper;

public class OpActivity extends Activity {
	private List<Bean> list = new ArrayList<Bean>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private Button btn_del;
	private ListHelpAdapter listHelpAdapter;
	private ListView lv_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_op);
		initView();
		initData();
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HashMap<Integer, Boolean> map = listHelpAdapter.getMap();
				int count = listHelpAdapter.getCount();
				for (int i = 0; i < count; i++) {
					if (map.get(i) != null && map.get(i)) {
						listHelpAdapter.delData(i);
					}
				}
				initData();
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		list.clear();
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
		listHelpAdapter = new ListHelpAdapter(getApplicationContext());
		listHelpAdapter.setData(list);
		lv_1.setAdapter(listHelpAdapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_del = (Button) findViewById(R.id.btn_delete);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) findViewById(R.id.listView1);
		list.clear();
	}
}
