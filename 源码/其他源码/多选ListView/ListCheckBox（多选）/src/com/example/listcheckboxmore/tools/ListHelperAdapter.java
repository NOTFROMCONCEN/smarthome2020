package com.example.listcheckboxmore.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.example.listcheckboxmore.R;

public class ListHelperAdapter extends BaseAdapter {

	private List<Bean> list = new ArrayList<Bean>();
	private HashMap<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();
	private Context mContext;

	public ListHelperAdapter(Context convertView) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = convertView;
		initCheck(false);
	}

	public HashMap<Integer, Boolean> getMap() {
		return isCheck;
	}

	public void initCheck(boolean flag) {
		for (int i = 0; i < list.size(); i++) {
			isCheck.put(i, flag);
		}
	}

	public void setData(List<Bean> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	public void delData(int postion) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?",
				new String[] { bean.getUser() });
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
				R.layout.user_item_view, null, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.cb_che = (CheckBox) view.findViewById(R.id.cb_che);
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.tv_pass.setText(bean.getPass());
		viewHolder.tv_user.setText(bean.getUser());
		if (bean.getOp().equals("op")) {
			viewHolder.tv_pass.setTextColor(Color.RED);
			viewHolder.tv_user.setTextColor(Color.RED);
			viewHolder.cb_che.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.tv_pass.setTextColor(Color.BLACK);
			viewHolder.tv_user.setTextColor(Color.BLACK);
			viewHolder.cb_che.setVisibility(View.VISIBLE);
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

	public class ViewHolder {
		TextView tv_user, tv_pass;
		CheckBox cb_che;
	}
}
