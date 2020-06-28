package com.example.shengsaib06282019.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}

}
