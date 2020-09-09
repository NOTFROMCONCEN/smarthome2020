package com.example.shengsaib06192019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���toast
 * @package_name com.example.shengsaib06192019.tools
 * @project_name 2019ʡ��B0619
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
		} else {
			toast.setText(s);
		}
		toast.show();
	}

}
