package com.example.shengsaic20180911.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaic20180911.R;
import com.example.shengsaic20180911.tools.AppReOnline;
import com.example.shengsaic20180911.tools.DiyToast;

public class SettingFragment extends Fragment {
	private Button btn_server_resert;
	private Button btn_exit;
	PopupWindow popupWindow;
	SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			final Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container,
				false);
		sharedPreferences = getActivity().getSharedPreferences("rember",
				getActivity().MODE_PRIVATE);
		btn_server_resert = (Button) view.findViewById(R.id.btn_resert_server);
		btn_exit = (Button) view.findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sharedPreferences.edit().putBoolean("autologin", false)
						.putBoolean("rember", false).putString("user", "")
						.putString("pass", "").putString("ip", "").commit();
				getActivity().finish();
			}
		});
		btn_server_resert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show_Pop();
			}
		});
		return view;
	}

	private void show_Pop() {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = new LayoutInflater(getActivity()) {

			@Override
			public LayoutInflater cloneInContext(Context newContext) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		View view = layoutInflater.inflate(R.layout.activity_server_resert,
				null, false);
		final Button btn_con = (Button) view.findViewById(R.id.btn_server_con);
		final EditText et_ip = (EditText) view.findViewById(R.id.et_resert_ip);
		final EditText et_port = (EditText) view
				.findViewById(R.id.et_resert_port);
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "«Î ‰»Î ˝÷µ");
				} else {
					sharedPreferences.edit()
							.putString("ip", et_ip.getText().toString())
							.commit();
					SocketClient.getInstance().ip = et_ip.getText().toString();
					AppReOnline.check(getActivity());
					popupWindow.dismiss();
				}
			}
		});
		popupWindow = new PopupWindow(view, 300, 400, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
				(Bitmap) null));
		popupWindow.showAsDropDown(btn_server_resert);
	}
}
