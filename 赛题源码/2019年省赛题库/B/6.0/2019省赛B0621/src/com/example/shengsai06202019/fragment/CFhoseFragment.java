package com.example.shengsai06202019.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shengsai06202019.R;
import com.example.shengsai06202019.tools.AppConfig;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ѡ��
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019ʡ��0620
 * @file_name CFhoseFragment.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class CFhoseFragment extends Fragment {

	private LinearLayout line_base, line_link, line_mode, line_draw;
	private TextView tv_chose_time;
	private ImageView image_base, image_link, image_mode, image_draw;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_chose, null, false);
		initView(view);// �󶨿ؼ�
		/**
		 * ����Ĭ����ʾ����
		 */
		image_base.setVisibility(View.VISIBLE);
		image_link.setVisibility(View.INVISIBLE);
		image_mode.setVisibility(View.INVISIBLE);
		image_draw.setVisibility(View.INVISIBLE);
		// ��������
		line_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub��
				BarActivity.mViewPager.setCurrentItem(1);
				image_base.setVisibility(View.VISIBLE);
				image_link.setVisibility(View.INVISIBLE);
				image_mode.setVisibility(View.INVISIBLE);
				image_draw.setVisibility(View.INVISIBLE);
			}
		});
		// ��ͼ����
		line_draw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BarActivity.mViewPager.setCurrentItem(4);
				image_base.setVisibility(View.INVISIBLE);
				image_link.setVisibility(View.INVISIBLE);
				image_mode.setVisibility(View.INVISIBLE);
				image_draw.setVisibility(View.VISIBLE);
			}
		});
		// ��������
		line_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BarActivity.mViewPager.setCurrentItem(2);
				image_base.setVisibility(View.INVISIBLE);
				image_link.setVisibility(View.VISIBLE);
				image_mode.setVisibility(View.INVISIBLE);
				image_draw.setVisibility(View.INVISIBLE);
			}
		});
		// ģʽ����
		line_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BarActivity.mViewPager.setCurrentItem(3);
				image_base.setVisibility(View.INVISIBLE);
				image_link.setVisibility(View.INVISIBLE);
				image_mode.setVisibility(View.VISIBLE);
				image_draw.setVisibility(View.INVISIBLE);
			}
		});
		// �����߳�
		handler.post(timeRunnable);
		return view;
	}

	/**
	 * ����ʱ��
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_chose_time.setText(AppConfig.time);
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

	private void initView(View view) {
		// TODO Auto-generated method stub
		line_base = (LinearLayout) view.findViewById(R.id.line_base);
		line_draw = (LinearLayout) view.findViewById(R.id.line_draw);
		line_link = (LinearLayout) view.findViewById(R.id.line_link);
		line_mode = (LinearLayout) view.findViewById(R.id.line_mode);
		image_base = (ImageView) view.findViewById(R.id.iv_base);
		image_draw = (ImageView) view.findViewById(R.id.iv_draw);
		image_link = (ImageView) view.findViewById(R.id.iv_link);
		image_mode = (ImageView) view.findViewById(R.id.iv_mode);
		tv_chose_time = (TextView) view.findViewById(R.id.tv_chose_time);
	}

}
