package com.example.shengsaia06062019.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 数据库
 * @package_name com.example.shengsaia06062019.sql
 * @project_name 2019ShengSaiA0606
 * @file_name MySQL.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MySQL extends SQLiteOpenHelper {

	public MySQL(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 循环节省时间
		for (int i = 1; i < 5; i++) {// 循环1~4
			for (int j = 1; j < 5; j++) {// 循环1~4
				// 创建数据库
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ " (_id integer primary key autoincrement,room_state text)");
				/**
				 * 0 - 未入住 1 - 已入住 2 - 未打扫
				 */
				// 插入数据
				db.execSQL("insert into room_8" + String.valueOf(i) + "0"
						+ String.valueOf(j) + "(room_state)values(?)",
						new String[] { "0" });
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
