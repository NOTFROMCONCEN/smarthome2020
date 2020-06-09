package com.example.shengsaia06062019.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ݿ�
 * @package_name com.example.shengsaia06062019.sql
 * @project_name 2019ShengSaiA0606
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
		// ѭ����ʡʱ��
		for (int i = 1; i < 5; i++) {// ѭ��1~4
			for (int j = 1; j < 5; j++) {// ѭ��1~4
				// �������ݿ�
				db.execSQL("create table room_8"
						+ String.valueOf(i)
						+ "0"
						+ String.valueOf(j)
						+ " (_id integer primary key autoincrement,room_state text)");
				/**
				 * 0 - δ��ס 1 - ����ס 2 - δ��ɨ
				 */
				// ��������
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
