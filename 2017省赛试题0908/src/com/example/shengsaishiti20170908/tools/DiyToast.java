package com.example.shengsaishiti20170908.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shengsaishiti20170908.R;

public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String string, int state) {

		View view = LayoutInflater.from(context).inflate(
				R.layout.activity_toast, null);
		ImageView iv_show = (ImageView) view.findViewById(R.id.iv_show);
		TextView tv_show = (TextView) view.findViewById(R.id.tv_show);
		toast = null;
		toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		toast.setView(view);
		tv_show.setText(string);
		if (state == 1) {
			toast.setView(view);
			tv_show.setText(string);
			iv_show.setImageResource(R.drawable.iv_false);
		}
		if (state == 2) {
			toast.setView(view);
			tv_show.setText(string);
			iv_show.setImageResource(R.drawable.iv_true);
		}
		if (state == 3) {
			toast.setView(view);
			tv_show.setText(string);
			iv_show.setVisibility(View.GONE);
		}
		// toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}
}
