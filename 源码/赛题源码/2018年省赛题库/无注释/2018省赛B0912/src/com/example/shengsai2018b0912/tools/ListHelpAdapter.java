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
 * @Todo TODO ListView自定义Adapter
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018省赛B0912
 * @file_name ListHelpAdapter.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class ListHelpAdapter extends BaseAdapter {

	public Context mContext;
	@SuppressLint("UseSparseArrays")
	public HashMap<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();
	public List<Bean> list = new ArrayList<Bean>();

	/**
	 * 批量设置复选框状态的方法
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
	 * 用于传递isCheck HashMap内容的方法
	 * 
	 * 获取isCheck内容
	 * 
	 * @return
	 */
	public HashMap<Integer, Boolean> getMap() {
		return isCheck;
	}

	/**
	 * 删除数据的方法
	 * 
	 * 获取list中的内容，之后从数据库中删除
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
			DiyToast.showToast(mContext, "你不能删除管理员账户");
		} else {
			db.execSQL("delete from user where username = ?",
					new String[] { bean.getUser() });
		}
	}

	/**
	 * 传递list内容的方法
	 * 
	 * 设置List列表数据
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
		// 传递Context
		this.mContext = convertView;
		// 设置默认状态（全部取消选中）
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

	// ViewHolder用于新建控件
	public class ViewHolder {
		TextView tv_user, tv_pass;
		CheckBox cb_che;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 新建View用于绑定界面、控件
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.activity_op_text, null, false);
		ViewHolder viewHolder = new ViewHolder();
		// 复选框
		viewHolder.cb_che = (CheckBox) view.findViewById(R.id.checkBox1);
		// 用户名（文本）
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_username);
		// 密码（文本）
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		// 使用bean赋值来获取这一行list中的各项数据
		Bean bean = list.get(position);
		// 设置密码
		viewHolder.tv_pass.setText(bean.getPass());
		// 设置用户名
		viewHolder.tv_user.setText(bean.getUser());
		// 判断当前用户是否为管理员权限
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
