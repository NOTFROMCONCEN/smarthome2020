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
 * @Todo TODO ��������
 * @package_name com.example.shengsai06202019
 * @project_name 2019ʡ��0620
 * @file_name UnLockActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class UnLockActivity extends Activity {

	private SeekBar sk_1;// ������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_lock);
		// ��
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// ����Ĭ�Ͻ���
		sk_1.setProgress(0);
		// ����������
		sk_1.setMax(100);
		// ����OnSeek����
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// �жϽ���
				if (seekBar.getProgress() == 100) {
					// ��ת
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				} else {
					// �������������ý���
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "����ɻ�������");
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
