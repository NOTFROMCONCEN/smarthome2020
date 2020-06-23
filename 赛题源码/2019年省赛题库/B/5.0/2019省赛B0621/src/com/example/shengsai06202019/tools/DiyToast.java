package com.example.shengsai06202019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019省赛0620
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {

	// 创建
	private static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			// 创建一个Toast
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		} else {
			// 设置显示文本
			toast.setText(text);
		}
		// 显示Toast
		toast.show();
	}

}
