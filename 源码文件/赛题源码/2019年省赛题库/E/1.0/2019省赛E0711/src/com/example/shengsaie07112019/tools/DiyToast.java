package com.example.shengsaie07112019.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

}
