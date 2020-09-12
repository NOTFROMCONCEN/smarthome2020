package com.example.listcheckboxonly;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.listcheckboxonly.tools.Bean;
import com.example.listcheckboxonly.tools.ListHelperAdapter;
import com.example.listcheckboxonly.tools.MyDataBaseHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 程序主入口 主页
 * @package_name com.example.listcheckboxonly
 * @project_name ListCheckBox（单选）
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	private List<Bean> list = new ArrayList<Bean>();
	private ListHelperAdapter listHelperAdapter;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private ListView lv_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		lv_1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				listHelperAdapter.delData(arg2);
				initData();
				return true;
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
		listHelperAdapter.setData(list);
		lv_1.setAdapter(listHelperAdapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) findViewById(R.id.listView1);
		listHelperAdapter = new ListHelperAdapter(getApplicationContext());
	}
}
