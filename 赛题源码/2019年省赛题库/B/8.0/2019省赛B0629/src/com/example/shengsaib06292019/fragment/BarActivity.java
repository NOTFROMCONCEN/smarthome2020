package com.example.shengsaib06292019.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.shengsaib06292019.R;
import com.example.shengsaib06292019.R.id;
import com.example.shengsaib06292019.R.layout;
import com.example.shengsaib06292019.R.menu;
import com.example.shengsaib06292019.R.string;
import com.example.shengsaib06292019.tools.AppConfig;
import com.example.shengsaib06292019.tools.SocketLink;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.AvoidXfermode.Mode;
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
	public static ViewPager mViewPager;
	private List<Fragment> faFragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		faFragments.add(new ChoseFragment());
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
					}
				});
		mViewPager.setOffscreenPageLimit(faFragments.size());
		SocketLink.start(BarActivity.this);
		actionBar.addTab(actionBar.newTab().setText("ѡ��").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("����").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("����").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("ģʽ").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("��ͼ").setTabListener(this));
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			ChoseFragment.tv_time.setText(AppConfig.time);
			DrawFragment.tv_time.setText(AppConfig.time);
			BaseFragment.tv_time.setText(AppConfig.time);
			ModeFragment.tv_time.setText(AppConfig.time);
			LinkFragment.tv_time.setText(AppConfig.time);
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
