package com.example.drawzhuzhuangmoredata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQL extends SQLiteOpenHelper {

	public MySQL(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table data (_id integer primary key autoincrement,data1 text,data2 text,data3 text,data4 text,data5 text)");
		for (int i = 1; i <= 5; i++) {
			db.execSQL("insert into data (data" + String.valueOf(i)
					+ ")values(?)", new String[] { "0" });
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
