package com.example.guosaic20190925;

import com.example.guosaic20190925.fragment.BarActivity;
import com.example.guosaic20190925.tools.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 解锁
 * 
 * @author 10976
 * 
 */
public class UnLockActivity extends Activity {

	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_lock);
		// 绑定
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// 滑动事件监听
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 判断状态
				if (sk_1.getProgress() != 100) {
					// 重置
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动解锁");
				} else {
					// 解锁跳转
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
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
