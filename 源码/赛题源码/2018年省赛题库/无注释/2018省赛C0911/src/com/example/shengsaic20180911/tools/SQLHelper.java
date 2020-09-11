package com.example.shengsaic20180911.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLHelper {

	private static MyDataBaseHelper dbHelper;
	private static SQLiteDatabase db;

	private static void get(Context context) {
		dbHelper = new MyDataBaseHelper(context, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}

	public static void jihuo(Context context) {
		get(context);
	}

	public static void insert(Context context, String user, String pass) {
		get(context);
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { user, pass, "user" });
	}

	public static boolean raw(Context context, String user, String pass) {
		Cursor cursor = db.rawQuery(
				"select * from user where username = ? and passward = ?",
				new String[] { user, pass });
		if (cursor.moveToNext()) {
			return true;
		} else {
			return false;
		}
	}

}
