package com.example.shengsai2018c0902.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {
	private static Toast toast;

	public static void showTaost(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}
}