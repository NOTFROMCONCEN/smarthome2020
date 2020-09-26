package com.example.guosaic20190925.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaic20190925.R;
import com.example.guosaic20190925.tools.DiyToast;
import com.example.guosaic20190925.tools.TimeTherad;

public class BarActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	List<Fragment> faFragments = new ArrayList<Fragment>();
	public static ViewPager mViewPager;
	TextView tv_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		tv_time = (TextView) findViewById(R.id.tv_bar_time);
		TimeTherad.start(tv_time);
		faFragments.add(new MenuFragment());
		faFragments.add(new BaseFragment());
		faFragments.add(new LinkFragment());
		faFragments.add(new ModeFragment());
		faFragments.add(new DrawFragment());
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
						if (position == 1) {
							tv_time.setTextColor(Color.WHITE);
						} else {
							tv_time.setTextColor(Color.BLACK);
						}
					}
				});
		mViewPager.setOffscreenPageLimit(faFragments.size());
		actionBar.addTab(actionBar.newTab().setText("选择").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("基本").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("联动").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("模式").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("绘图").setTabListener(this));
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(getApplicationContext(),
									"网络连接成功");
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
						}
					}
				});
			}
		});
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
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
