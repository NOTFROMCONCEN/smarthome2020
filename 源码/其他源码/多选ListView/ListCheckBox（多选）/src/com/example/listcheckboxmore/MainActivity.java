package com.example.listcheckboxmore;

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

import com.example.listcheckboxmore.tools.Bean;
import com.example.listcheckboxmore.tools.ListHelperAdapter;
import com.example.listcheckboxmore.tools.MyDataBaseHelper;

public class MainActivity extends Activity {

	private List<Bean> list = new ArrayList<Bean>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private ListHelperAdapter listHelperAdapter;
	private ListView listView;
	private Button btn_del;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HashMap<Integer, Boolean> map = listHelperAdapter.getMap();
				int count = listHelperAdapter.getCount();
				for (int i = 0; i < count; i++) {
					if (map.get(i) != null && map.get(i)) {
						listHelperAdapter.delData(i);
					}
				}
				initData();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_del = (Button) findViewById(R.id.btn_del);
		listView = (ListView) findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	private void initData() {
		// TODO Auto-generated method stub
		list.clear();
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward")), cursor.getString(cursor
						.getColumnIndex("op_state"))));
			}
		}
		listHelperAdapter = new ListHelperAdapter(getApplicationContext());
		listHelperAdapter.setData(list);
		listView.setAdapter(listHelperAdapter);
	}
}
