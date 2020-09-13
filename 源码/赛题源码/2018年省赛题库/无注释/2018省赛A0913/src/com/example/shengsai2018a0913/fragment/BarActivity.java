package com.example.shengsai2018a0913.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018a0913.R;
import com.example.shengsai2018a0913.tools.DiyToast;

public class BarActivity extends FragmentActivity {

	SectionsPagerAdapter mSectionsPagerAdapter;
	List<Fragment> faFragments = new ArrayList<Fragment>();
	ViewPager mViewPager;
	public static RadioButton ra_user, ra_link, ra_mode, ra_base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		ra_base = (RadioButton) findViewById(R.id.ra_base);
		ra_link = (RadioButton) findViewById(R.id.ra_link);
		ra_mode = (RadioButton) findViewById(R.id.ra_mode);
		ra_user = (RadioButton) findViewById(R.id.ra_user);
		faFragments.add(new BaseActivity());
		faFragments.add(new LinkActivity());
		faFragments.add(new ModeActivity());
		faFragments.add(new UserActivity());
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(faFragments.size());
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
		ra_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(2);
			}
		});
		ra_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(3);
			}
		});
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String paramString) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub\
						if (paramString.equals(ConstantUtil.SUCCESS)) {
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
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						switch (position) {
						case 0:
							ra_base.setChecked(true);
							break;
						case 1:
							ra_link.setChecked(true);
							break;
						case 2:
							ra_mode.setChecked(true);
							break;
						case 3:
							ra_user.setChecked(true);
							break;
						default:
							break;
						}
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
