package com.example.shengsaia06132019.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ݿ�
 * @package_name com.example.shengsaia06132019.tools
 * @project_name 2019ShengSaiA0613
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
		// ѭ�����ɳ�
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				// �������ݿ�
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ "(_id integer primary key autoincrement,room_state text)");
				// �������ݿ�
				db.execSQL("insert into room_8" + String.valueOf(i) + "0"
						+ String.valueOf(j) + "(room_state)values(1)");
			}
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
