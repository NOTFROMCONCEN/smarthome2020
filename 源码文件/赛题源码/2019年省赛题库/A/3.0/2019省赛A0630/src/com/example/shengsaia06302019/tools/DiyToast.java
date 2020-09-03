package com.example.shengsaia06302019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsaia06302019.tools
 * @project_name 2019省赛A0630
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {
	// 新建Toast
	private static Toast toast;

	/**
	 * 显示Toast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showToast(Context context, String string) {
		// 判断toast是不是空
		if (toast == null) {
			// 新建Toast
			toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
		} else {
			// 设置Toast文本
			toast.setText(string);
		}
		// 显示Toast
		toast.show();
	}

}
