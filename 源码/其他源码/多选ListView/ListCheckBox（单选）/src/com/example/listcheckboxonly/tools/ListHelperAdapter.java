package com.example.listcheckboxonly.tools;

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

import com.example.listcheckboxonly.R;

public class ListHelperAdapter extends BaseAdapter {

	private List<Bean> list = new ArrayList<Bean>();
	private Context mContext;

	public void delData(int Integer) {
		// TODO Auto-generated method stub
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(Integer);
		db.execSQL("delete from user where username = ?",
				new String[] { bean.getUser() });
	}

	public void setData(List<Bean> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	public ListHelperAdapter(Context convertView) {
		// TODO Auto-generated constructor stub
		this.mContext = convertView;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.user_list_view, null, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.tv_op = (TextView) view.findViewById(R.id.tv_op);
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.tv_pass.setText(bean.getPass());
		viewHolder.tv_user.setText(bean.getUser());
		if (bean.getOp().equals("op")) {
			viewHolder.tv_op.setText("管理员");
			viewHolder.tv_op.setTextColor(Color.RED);
			viewHolder.tv_pass.setTextColor(Color.RED);
			viewHolder.tv_user.setTextColor(Color.RED);
		} else {
			viewHolder.tv_op.setText("用户");
			viewHolder.tv_op.setTextColor(Color.BLACK);
			viewHolder.tv_pass.setTextColor(Color.BLACK);
			viewHolder.tv_user.setTextColor(Color.BLACK);
		}
		return view;
	}

	public class ViewHolder {
		public TextView tv_user, tv_pass, tv_op;
	}
}
