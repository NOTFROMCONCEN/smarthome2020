package com.example.shengsai2019e0921.tools;

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
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,twopass text)");
		db.execSQL("insert into user (username,passward,twopass)values(?,?,?)",
				new String[] { "bizideal", "123456", "123" });
		db.execSQL("create table link_mode (_id integer primary key autoincrement,"
				+ "link_name text,"
				+ "link_chuanganqi text,"
				+ "link_big_small text,"
				+ "link_number text,"
				+ "link_shebei text)");

		db.execSQL("create table lamp_l_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table lamp_r_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table tv_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table dvd_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table kt_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table cur_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table door_state (_id integer primary key autoincrement,state text)");
		db.execSQL("create table fan_state (_id integer primary key autoincrement,state text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
