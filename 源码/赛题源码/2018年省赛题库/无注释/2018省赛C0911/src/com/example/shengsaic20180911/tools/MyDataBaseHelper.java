package com.example.shengsaic20180911.tools;

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
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,op text)");
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "bizideal", "123456", "op" });
		for (int i = 0; i < 50; i++) {
			db.execSQL("insert into user (username,passward,op)values(?,?,?)",
					new String[] { "user" + String.valueOf(i), "123456", "user" });
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
