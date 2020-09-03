package com.etang.serverdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.etang.serverdemo.LinkTools.ControlUtils;
import com.etang.serverdemo.LinkTools.DataCallback;
import com.etang.serverdemo.LinkTools.DeviceBean;
import com.etang.serverdemo.LinkTools.LoginCallback;
import com.etang.serverdemo.LinkTools.SocketClient;

public class MainActivity extends Activity {

	TextView tv_link_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ControlUtils.setUser("bizideal", "123456", "18.1.10.2");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String paramString) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println(paramString);
						tv_link_state.setText(paramString);
					}
				});
			}
		});
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onEvent(String paramString) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onResult(DeviceBean getdata) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(getdata.getAirPressure())) {
					System.out.println(getdata.getAirPressure());
				}
			}
		});
	}
}
