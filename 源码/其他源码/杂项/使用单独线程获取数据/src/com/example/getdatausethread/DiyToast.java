package com.example.getdatausethread;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.getdatausethread
 * @project_name 使用单独线程获取数据
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {
	private static Toast toast;// 定义Toast

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
		} else {
			toast.setText(s);
		}
		toast.show();
	}
}
