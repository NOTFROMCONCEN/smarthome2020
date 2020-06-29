package com.example.shengsaib06292019.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {
	private static Toast toast;

	public static void showToast(Context c, String s) {
		if (toast == null) {
			toast = Toast.makeText(c, s, Toast.LENGTH_SHORT);
		} else {
			toast.setText(s);
		}
		toast.show();
	}
}
