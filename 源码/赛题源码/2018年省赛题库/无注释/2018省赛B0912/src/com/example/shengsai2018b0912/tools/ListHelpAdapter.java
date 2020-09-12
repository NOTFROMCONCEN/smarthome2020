package com.example.shengsai2018b0912.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.shengsai2018b0912.R;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ListView�Զ���Adapter
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018ʡ��B0912
 * @file_name ListHelpAdapter.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class ListHelpAdapter extends BaseAdapter {

	public Context mContext;
	@SuppressLint("UseSparseArrays")
	public HashMap<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();
	public List<Bean> list = new ArrayList<Bean>();

	/**
	 * �������ø�ѡ��״̬�ķ���
	 * 
	 * @param flag
	 */
	private void initCheck(boolean flag) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			isCheck.put(i, flag);
		}
	}

	/**
	 * ���ڴ���isCheck HashMap���ݵķ���
	 * 
	 * ��ȡisCheck����
	 * 
	 * @return
	 */
	public HashMap<Integer, Boolean> getMap() {
		return isCheck;
	}

	/**
	 * ɾ�����ݵķ���
	 * 
	 * ��ȡlist�е����ݣ�֮������ݿ���ɾ��
	 * 
	 * @param postion
	 */
	public void delData(int postion) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		if (bean.getOp().equals("op")) {
			DiyToast.showToast(mContext, "�㲻��ɾ������Ա�˻�");
		} else {
			db.execSQL("delete from user where username = ?",
					new String[] { bean.getUser() });
		}
	}

	/**
	 * ����list���ݵķ���
	 * 
	 * ����List�б�����
	 * 
	 * @param list
	 */
	public void setData(List<Bean> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	public ListHelpAdapter(Context convertView) {
		// TODO Auto-generated constructor stub
		super();
		// ����Context
		this.mContext = convertView;
		// ����Ĭ��״̬��ȫ��ȡ��ѡ�У�
		initCheck(false);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// ViewHolder�����½��ؼ�
	public class ViewHolder {
		TextView tv_user, tv_pass;
		CheckBox cb_che;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// �½�View���ڰ󶨽��桢�ؼ�
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.activity_op_text, null, false);
		ViewHolder viewHolder = new ViewHolder();
		// ��ѡ��
		viewHolder.cb_che = (CheckBox) view.findViewById(R.id.checkBox1);
		// �û������ı���
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_username);
		// ���루�ı���
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		// ʹ��bean��ֵ����ȡ��һ��list�еĸ�������
		Bean bean = list.get(position);
		// ��������
		viewHolder.tv_pass.setText(bean.getPass());
		// �����û���
		viewHolder.tv_user.setText(bean.getUser());
		// �жϵ�ǰ�û��Ƿ�Ϊ����ԱȨ��
		if (bean.getOp().equals("op")) {
			viewHolder.cb_che.setVisibility(View.INVISIBLE);
			viewHolder.tv_pass.setTextColor(Color.RED);
			viewHolder.tv_user.setTextColor(Color.RED);
		} else {
			viewHolder.cb_che.setVisibility(View.VISIBLE);
			viewHolder.tv_pass.setTextColor(Color.BLACK);
			viewHolder.tv_user.setTextColor(Color.BLACK);
		}
		viewHolder.cb_che
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							isCheck.put(position, isChecked);
						} else {
							isCheck.put(position, isChecked);
						}
					}
				});
		if (isCheck.get(position) == null) {
			isCheck.put(position, false);
		}
		viewHolder.cb_che.setChecked(isCheck.get(position));
		return view;
	}
}
