package com.example.shengsaic20180911.tools;

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

import com.example.shengsaic20180911.R;

public class ListBaseAdapter extends BaseAdapter {

	private List<Bean> list = new ArrayList<Bean>();
	private Context mContext;

	@SuppressLint("UseSparseArrays")
	private HashMap<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();

	public HashMap<Integer, Boolean> getMap() {
		return isCheck;
	}

	public void initCheck(boolean flag) {
		for (int i = 0; i < list.size(); i++) {
			isCheck.put(i, flag);
		}
	}

	public static class ViewHolder {
		public TextView user;
		public TextView pass;
		public CheckBox checkBox;
	}

	public ListBaseAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		initCheck(false);
	}

	public void setData(List<Bean> data) {
		this.list = data;
	}

	public void addData(Bean bean) {
		list.add(0, bean);
	}

	public void delData(int postion) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?", new String[] { bean
				.getUser().toString() });
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.activity_op_text, null);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.user = (TextView) view.findViewById(R.id.tv_username);
		viewHolder.pass = (TextView) view.findViewById(R.id.tv_passward);
		viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.user.setText(bean.getUser().toString());
		viewHolder.pass.setText(bean.getPass().toString());
		if (viewHolder.user.getText().toString().equals("bizideal")) {
			viewHolder.user.setTextColor(Color.RED);
			viewHolder.pass.setTextColor(Color.RED);
			viewHolder.checkBox.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.user.setTextColor(Color.BLACK);
			viewHolder.pass.setTextColor(Color.BLACK);
			viewHolder.checkBox.setVisibility(View.VISIBLE);
		}
		viewHolder.checkBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						isCheck.put(position, isChecked);
					}
				});
		if (isCheck.get(position) == null) {
			isCheck.put(position, false);
		}
		viewHolder.checkBox.setChecked(isCheck.get(position));
		return view;
	}

}
