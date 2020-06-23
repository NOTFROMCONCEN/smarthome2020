package com.example.shengsaib06232019;

import com.example.shengsaib06232019.tools.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class UnLockActivity extends Activity {

	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_lock);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setMax(100);
		sk_1.setProgress(0);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "请完成滑动解锁");
					sk_1.setProgress(0);
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