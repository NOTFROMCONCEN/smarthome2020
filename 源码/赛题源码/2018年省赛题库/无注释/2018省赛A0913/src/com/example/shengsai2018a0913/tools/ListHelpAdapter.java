package com.example.shengsai2018a0913.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shengsai2018a0913.R;

public class ListHelpAdapter extends BaseAdapter {

	public Context context;
	public List<Bean> list = new ArrayList<Bean>();

	public ListHelpAdapter(Context convertView) {
		// TODO Auto-generated constructor stub
		this.context = convertView;
	}

	public void delData(int postion) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?",
				new String[] { bean.getUser() });
	}

	public void setData(List<Bean> list) {
		// TODO Auto-generated method stub
		this.list = list;
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

	public class ViewHolder {
		TextView tv_user, tv_pass, tv_op;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(
				R.layout.activity_text, null, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.tv_op = (TextView) view.findViewById(R.id.tv_op);
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		Bean bean = list.get(position);
		viewHolder.tv_pass.setText(bean.getPass());
		viewHolder.tv_user.setText(bean.getUser());
		if (bean.getOp().equals("op")) {
			viewHolder.tv_op.setText("管理员");
			viewHolder.tv_pass.setTextColor(Color.RED);
			viewHolder.tv_user.setTextColor(Color.RED);
			viewHolder.tv_op.setTextColor(Color.RED);
		} else {
			viewHolder.tv_op.setText("用户");
			viewHolder.tv_pass.setTextColor(Color.BLACK);
			viewHolder.tv_user.setTextColor(Color.BLACK);
			viewHolder.tv_op.setTextColor(Color.BLACK);
		}
		// view.setTag(viewHolder);
		// viewHolder = (viewHolder) view.getTag();
		return view;
	}
}
