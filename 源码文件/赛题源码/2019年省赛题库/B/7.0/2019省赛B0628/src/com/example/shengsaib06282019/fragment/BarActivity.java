package com.example.shengsaib06282019.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.shengsaib06282019.R;
import com.example.shengsaib06282019.R.id;
import com.example.shengsaib06282019.R.layout;
import com.example.shengsaib06282019.R.menu;
import com.example.shengsaib06282019.R.string;
import com.example.shengsaib06282019.tools.AppConfig;
import com.example.shengsaib06282019.tools.SocketLink;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BarActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	static ViewPager mViewPager;
	List<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		final ActionBar actionBar = getActionBar();
		fragments.add(new ChoseFragment());
		fragments.add(new BaseFragment());
		fragments.add(new LinkFragment());
		fragments.add(new ModeFragment());
		fragments.add(new DrawFragment());
		SocketLink.start(BarActivity.this);
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
					}
				});
		mViewPager.setOffscreenPageLimit(fragments.size());
		actionBar.addTab(actionBar.newTab().setText("选择").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("基本").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("联动").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("模式").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("绘图").setTabListener(this));
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			ChoseFragment.tv_chose_time.setText(AppConfig.time);
			BaseFragment.tv_base_time.setText(AppConfig.time);
			ModeFragment.tv_mode_time.setText(AppConfig.time);
			DrawFragment.tv_time.setText(AppConfig.time);
			LinkFragment.tv_link_time.setText(AppConfig.time);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bar, menu);
		return true;
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
			return fragments.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}
	}
}
