package com.example.shengsai2019a0914.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

	public MyDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				// 创建数据库
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ "(_id integer primary key autoincrement,room_state text)");
				// 1--未入住 2--未打扫 3--已入住
				// 插入数据库
				db.execSQL("insert into room_8" + String.valueOf(i) + "0"
						+ String.valueOf(j) + "(room_state)values(?)",
						new String[] { "1" });
			}
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
