package com.example.shengsaia06132019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsaia06132019.tools
 * @project_name 2019ShengSaiA0613
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
		} else {
			toast.setText(s);
		}
		toast.show();
	}

}
