package com.example.shengsai06202019.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shengsai06202019.R;
import com.example.shengsai06202019.tools.AppConfig;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��ͼ
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019ʡ��0620
 * @file_name DrawFragment.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DrawFragment extends Fragment {
	private TextView tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_draw, null, false);
		// ��
		tv_time = (TextView) view.findViewById(R.id.tv_draw_time);
		// ��ȡʱ��
		handler.post(timeRunnable);
		return view;
	}

	/**
	 * ��ȡʱ�����
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_time.setText(AppConfig.time);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
