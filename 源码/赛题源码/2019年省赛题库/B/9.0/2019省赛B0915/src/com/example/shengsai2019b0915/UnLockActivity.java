package com.example.shengsai2019b0915;

import com.example.shengsai2019b0915.fragment.BarActivity;
import com.example.shengsai2019b0915.tools.DiyToast;

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
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (sk_1.getProgress() != 100) {
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
					sk_1.setProgress(0);
				} else {
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
