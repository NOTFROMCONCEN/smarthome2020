package com.example.guosai2019d0926.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

}
