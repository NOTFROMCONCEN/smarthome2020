package com.example.shengsaid07032019.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

}
