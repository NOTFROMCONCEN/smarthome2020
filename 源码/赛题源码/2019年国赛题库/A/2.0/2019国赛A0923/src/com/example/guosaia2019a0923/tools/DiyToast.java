package com.example.guosaia2019a0923.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义Toast提示
 * 
 * @author 10976
 * 
 */
public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}

}
