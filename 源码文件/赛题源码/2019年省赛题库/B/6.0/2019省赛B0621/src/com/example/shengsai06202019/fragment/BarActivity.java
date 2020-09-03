package com.example.shengsai06202019.fragment;

import java.util.ArrayList;
import java.util.List;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai06202019.R;
import com.example.shengsai06202019.R.id;
import com.example.shengsai06202019.R.layout;
import com.example.shengsai06202019.R.menu;
import com.example.shengsai06202019.R.string;
import com.example.shengsai06202019.tools.SocketLink;

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

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 滑动栏
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019省赛0620
 * @file_name BarActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class BarActivity extends FragmentActivity implements
		ActionBar.TabListener {
	SectionsPagerAdapter mSectionsPagerAdapter;
	static ViewPager mViewPager;
	// 新建列表用于存储碎片
	private List<Fragment> faFragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		/**
		 * 添加碎片
		 */
		faFragments.add(new CFhoseFragment());
		faFragments.add(new BaseFragment());
		faFragments.add(new LinkFragment());
		faFragments.add(new ModeFragment());
		faFragments.add(new DrawFragment());
		// 启动联网、数据采集线程
		SocketLink.start_web(BarActivity.this);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(faFragments.size());
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});
		// 添加titleBar标识
		actionBar.addTab(actionBar.newTab().setText("选择").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("基本").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("联动").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("模式").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("绘图").setTabListener(this));
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
