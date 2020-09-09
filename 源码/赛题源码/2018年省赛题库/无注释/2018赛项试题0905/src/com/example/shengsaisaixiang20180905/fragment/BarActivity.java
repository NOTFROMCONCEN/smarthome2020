package com.example.shengsaisaixiang20180905.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaisaixiang20180905.R;
import com.example.shengsaisaixiang20180905.tools.DiyToast;
import com.example.shengsaisaixiang20180905.tools.UserConfig;

public class BarActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	List<Fragment> faFragments = new ArrayList<Fragment>();
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	public static ViewPager mViewPager;
	public static RadioButton ra_base, ra_link, ra_op, ra_setting;
	RadioButton[] rdo = new RadioButton[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		faFragments.add(new BaseFragment());
		faFragments.add(new LinkFragment());
		faFragments.add(new OpFragment());
		faFragments.add(new SettingFragment());
		ra_base = (RadioButton) findViewById(R.id.ra_base);
		ra_link = (RadioButton) findViewById(R.id.ra_link);
		ra_op = (RadioButton) findViewById(R.id.ra_op);
		ra_setting = (RadioButton) findViewById(R.id.ra_setting);
		rdo[0] = ra_base;
		rdo[1] = ra_link;
		rdo[2] = ra_op;
		rdo[3] = ra_setting;
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		rdo[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(0);
			}
		});
		rdo[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
			}
		});
		rdo[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(2);
				if (BarActivity.ra_op.isChecked()) {
					if (UserConfig.op_state == false) {
						new AlertDialog.Builder(BarActivity.this)
								.setTitle("提示")
								.setMessage("你非管理员账号不能进行该操作")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated
												// method stub
												BarActivity.mViewPager
														.setCurrentItem(0);
												BarActivity.ra_base
														.setChecked(true);
											}
										}).show();
					}
				}
			}
		});
		rdo[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(3);
			}
		});
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				rdo[arg0].setChecked(true);
				if (arg0 == 2) {
					if (BarActivity.ra_op.isChecked()) {
						if (UserConfig.op_state == false) {
							new AlertDialog.Builder(BarActivity.this)
									.setTitle("提示")
									.setMessage("你非管理员账号不能进行该操作")
									.setPositiveButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													BarActivity.mViewPager
															.setCurrentItem(0);
													BarActivity.ra_base
															.setChecked(true);
												}
											}).show();
						}
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
		web_link(BarActivity.this);
	}

	public static void web_link(final Activity activity) {
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(activity, "网络连接成功");
						} else {
							DiyToast.showToast(activity, "网络连接失败");
						}
					}
				});
			}
		});
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return faFragments.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return faFragments.get(arg0);
		}

	}
}
