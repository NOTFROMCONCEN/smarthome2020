package com.example.shengsai2018a0901.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shengsai2018a0901.LoginActivity;
import com.example.shengsai2018a0901.R;
import com.example.shengsai2018a0901.tools.DiyToast;
import com.example.shengsai2018a0901.tools.MyDataBaseHelper;

public class UserActivity extends Fragment {
	private ListView lv_1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	static String username;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_user, container, false);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		getdata();
		lv_1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int postion, long id) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) parent.getItemAtPosition(postion);
				final String get_user = cursor.getString(1);
				final String get_pass = cursor.getString(2);
				new AlertDialog.Builder(getActivity()).setItems(
						new String[] { "����", "ɾ��" },
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								switch (which) {
								case 0:
									username = get_user;
									updata_pass_Dialog();
									break;
								case 1:
									if (get_user.equals("bizideal")) {
										DiyToast.showToast(getActivity(),
												"�㲻��ɾ������Ա�˺�");
									} else {
										db.execSQL(
												"delete from user where username = ? and passward = ?",
												new String[] { get_user,
														get_pass });
										LoginActivity.sharedPreferences.edit()
												.putBoolean("autologin", false)
												.putBoolean("rember", false)
												.putString("user", "")
												.putString("pass", "")
												.putString("ip", "").commit();
										getdata();
									}
									break;
								default:
									break;
								}
							}
						}).show();
				return false;
			}
		});
		return view;
	}

	private void updata_pass_Dialog() {
		DiyToast.showToast(getActivity(), username);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		final View view = layoutInflater.inflate(R.layout.activity_view_dialog,
				null, false);
		builder.setView(view);
		builder.setTitle("�޸�����");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_passward_get;
				et_passward_get = (EditText) view
						.findViewById(R.id.et_updata_pass);
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { username });
				if (cursor.moveToNext()) {
					db.execSQL(
							"update user set passward = ? where username = ?",
							new String[] {
									et_passward_get.getText().toString(),
									username });// �������ݿ�
					DiyToast.showToast(getActivity(), "�޸ĳɹ�");
					LoginActivity.sharedPreferences.edit()
							.putBoolean("autologin", false)
							.putBoolean("rember", false).putString("user", "")
							.putString("pass", "").putString("ip", "").commit();// �޸�ƫ�����ã�ȡ���Զ���¼��ס���룩
					getdata();// ����ListView

				}
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}

	private void getdata() {
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
				getActivity(), R.layout.activity_text, c, new String[] {
						"username", "passward", "op_state" }, new int[] {
						R.id.tv_user, R.id.tv_pass, R.id.tv_op });
		lv_1.setAdapter(cursorAdapter);
	}
}
