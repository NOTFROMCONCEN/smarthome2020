package com.example.shengsaid07032019.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.shengsaid07032019.R;
import com.example.shengsaid07032019.R.id;
import com.example.shengsaid07032019.R.layout;
import com.example.shengsaid07032019.R.menu;
import com.example.shengsaid07032019.R.string;
import com.example.shengsaid07032019.tools.AppTools;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
	private List<Fragment> faFragments = new ArrayList<Fragment>();
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		AppTools.start_link(BarActivity.this);
		faFragments.add(new BaseFragment());
		faFragments.add(new ControlFragment());
		faFragments.add(new LinkFragment());
		faFragments.add(new ModeFragment());
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
		actionBar.addTab(actionBar.newTab().setText("传感器数据")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("电器控制")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("联动控制")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("模式控制")
				.setTabListener(this));
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
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
			return faFragments.get(arg0);
		}

	}
}
