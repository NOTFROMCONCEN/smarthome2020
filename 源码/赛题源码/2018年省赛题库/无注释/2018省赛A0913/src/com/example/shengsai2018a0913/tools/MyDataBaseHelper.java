package com.example.shengsai2018a0913.tools;

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
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,op_state text)");
		db.execSQL(
				"insert into user (username,passward,op_state)values(?,?,?)",
				new String[] { "bizideal", "123456", "op" });
		for (int i = 0; i < 10; i++) {
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
