package com.etang.diytoast;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String string,
			boolean long_short) {
		if (toast == null) {
			if (long_short) {
				toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
			} else {
				toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
			}
		} else {
			toast.setText(string);
		}
		toast.show();
	}

}
