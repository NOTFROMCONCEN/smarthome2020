package com.example.codeforpass;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.codeforpass.toast.DiyToast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageButton imageButton = (ImageButton) findViewById(R.id.myImage);
		imageButton.setImageBitmap(createCode.createRandomBitmap());// ��������ʱˢ��һ����֤��
		findViewById(R.id.tv_repass_update).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						imageButton.setImageBitmap(createCode
								.createRandomBitmap());// ������֤��
						DiyToast.showToast(getApplicationContext(),
								createCode.code);// ������֤����ʾ
					}
				});
		findViewById(R.id.btn_con).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((EditText) findViewById(R.id.et_repass)).getText()
						.toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "��������֤��");
				} else {
					if (((EditText) findViewById(R.id.et_repass)).getText()
							.toString().equals(createCode.code)) {
						DiyToast.showToast(getApplicationContext(), "��֤�ɹ�");
					} else {
						DiyToast.showToast(getApplicationContext(), "��֤�����");
					}
				}
			}
		});
	}
}
