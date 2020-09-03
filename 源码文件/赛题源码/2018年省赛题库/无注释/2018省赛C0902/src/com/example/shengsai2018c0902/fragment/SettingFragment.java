package com.example.shengsai2018c0902.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;
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
import com.example.shengsai2018c0902.LoginActivity;
import com.example.shengsai2018c0902.R;
import com.example.shengsai2018c0902.tools.CheckOnLine;
import com.example.shengsai2018c0902.tools.DiyToast;

public class SettingFragment extends Fragment {
	private Button btn_server_resert;// 重置
	private Button btn_exit;// 退出
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
		// 绑定
		btn_server_resert = (Button) view.findViewById(R.id.btn_resert_server);
		btn_exit = (Button) view.findViewById(R.id.btn_exit);
		// 退出当前账号
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sharedPreferences.edit().putBoolean("autologin", false)
				// 自动登录false
						.putBoolean("rember", false)
						// 记住密码false
						.putString("user", "")// 用户名
						.putString("pass", "")// 密码
						.putString("ip", "").commit();// Ip地址
				getActivity().finish();
			}
		});
		// 重设服务器
		/**
		 * --------------------------------------------------------------------
		 * BUG:
		 * --------------------------------------------------------------------
		 * Popwindo无法获取焦点
		 */
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
					DiyToast.showTaost(getActivity(), "请输入数值");
				} else {
					sharedPreferences.edit()
							.putString("ip", et_ip.getText().toString())
							.commit();
					SocketClient.getInstance().ip = et_ip.getText().toString();
					DiyToast.showTaost(getActivity(), "请等待重连或者退出重新登录");
					CheckOnLine.check(getActivity());
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
