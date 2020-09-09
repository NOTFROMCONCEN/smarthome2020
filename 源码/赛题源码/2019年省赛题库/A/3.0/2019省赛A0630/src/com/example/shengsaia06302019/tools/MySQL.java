package com.example.shengsaia06302019.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ݿ�
 * @package_name com.example.shengsaia06302019.tools
 * @project_name 2019ʡ��A0630
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
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				// �������ݿ�
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ "(_id integer primary key autoincrement,room_state text)");
				// 0--δ��ס 1--δ��ɨ 2--����ס
				// �������ݿ�
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
