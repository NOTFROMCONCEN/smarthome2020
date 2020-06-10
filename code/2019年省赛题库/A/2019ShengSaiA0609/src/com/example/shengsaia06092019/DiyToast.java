package com.example.shengsaia06092019;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		} else {
			toast.setText(text);
		}
		toast.show();
	}
}