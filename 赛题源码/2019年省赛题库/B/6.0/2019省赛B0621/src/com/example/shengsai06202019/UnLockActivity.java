package com.example.shengsai06202019;

import com.example.shengsai06202019.fragment.BarActivity;
import com.example.shengsai06202019.tools.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 滑动解锁
 * @package_name com.example.shengsai06202019
 * @project_name 2019省赛0620
 * @file_name UnLockActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class UnLockActivity extends Activity {

	private SeekBar sk_1;// 滑动条

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_lock);
		// 绑定
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// 设置默认进度
		sk_1.setProgress(0);
		// 设置最大进度
		sk_1.setMax(100);
		// 设置OnSeek方法
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 判断进度
				if (seekBar.getProgress() == 100) {
					// 跳转
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				} else {
					// 不满足条件重置进度
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动解锁");
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
	}
}
