package com.example.shengsaia06062019;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
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
