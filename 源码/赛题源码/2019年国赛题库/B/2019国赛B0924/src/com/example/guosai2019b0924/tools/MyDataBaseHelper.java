package com.example.guosai2019b0924.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 * 
 * @author 10976
 * 
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
		// 创建每一个房间的数据库
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ "(_id integer primary key autoincrement,room_state text)");
			}
		}
		// 插入数据
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
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
