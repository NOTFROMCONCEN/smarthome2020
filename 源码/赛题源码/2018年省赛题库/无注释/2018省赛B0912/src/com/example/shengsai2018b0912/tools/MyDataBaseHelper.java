package com.example.shengsai2018b0912.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ݿ�
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018ʡ��B0912
 * @file_name MyDataBaseHelper.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

	public MyDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// ����һ�����ݿ�
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,op_state text)");
		// ����Ĭ�Ϲ���ԱȨ���˻�
		db.execSQL(
				"insert into user (username,passward,op_state)values(?,?,?)",
				new String[] { "bizideal", "123456", "op" });
		// ����Ĭ���û�Ȩ���˻�
		for (int i = 0; i < 50; i++) {
			db.execSQL(
					"insert into user (username,passward,op_state)values(?,?,?)",
					new String[] { "user" + String.valueOf(i), "123456", "user" });
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
