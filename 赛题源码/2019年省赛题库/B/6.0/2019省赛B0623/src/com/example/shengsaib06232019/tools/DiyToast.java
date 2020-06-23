package com.example.shengsaib06232019.tools;

import android.content.Context;
import android.widget.Toast;

public class DiyToast {

	private static Toast toast;

	public static void showToast(Context condition, String text) {
		if (toast == null) {
			toast = Toast.makeText(condition, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

}
