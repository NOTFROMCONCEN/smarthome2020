package com.example.shengsai06202019.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ݿ�
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019ʡ��B0620
 * @file_name MySQL.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
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
		// �������ݿ�
		db.execSQL("create table draw_data (_id integer primary key autoincrement,number text,data text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
