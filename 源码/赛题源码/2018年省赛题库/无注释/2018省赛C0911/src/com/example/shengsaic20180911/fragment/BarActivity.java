package com.example.shengsaic20180911.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaic20180911.R;
import com.example.shengsaic20180911.tools.AppConfig;
import com.example.shengsaic20180911.tools.AppReOnline;
import com.example.shengsaic20180911.tools.DiyToast;

public class BarActivity extends FragmentActivity {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	List<Fragment> faFragments = new ArrayList<Fragment>();
	RadioButton ra_base, ra_link, ra_op, ra_setting;
	private RadioButton[] rdo = new RadioButton[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		ra_base = (RadioButton) findViewById(R.id.ra_base);
		ra_link = (RadioButton) findViewById(R.id.ra_link);
		ra_op = (RadioButton) findViewById(R.id.ra_op);
		ra_setting = (RadioButton) findViewById(R.id.ra_setting);
		rdo[0] = ra_base;
		rdo[1] = ra_link;
		rdo[2] = ra_op;
		rdo[3] = ra_setting;
		ra_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(0);
			}
		});
		ra_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
			}
		});
		ra_op.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(2);
			}
		});
		ra_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(3);
			}
		});
		AppReOnline.check(BarActivity.this);
		faFragments.add(new BaseFragment());
		faFragments.add(new LinkFragment());
		faFragments.add(new OpFragment());
		faFragments.add(new SettingFragment());
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				rdo[arg0].setChecked(true);
				if (arg0 == 2) {
					if (!AppConfig.op) {
						new AlertDialog.Builder(BarActivity.this)
								.setTitle("提示")
								.setMessage("你非管理员账号不能进行该操作")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												mViewPager.setCurrentItem(0);
											}
										}).show();
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return faFragments.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return faFragments.get(arg0);
		}

	}
}
