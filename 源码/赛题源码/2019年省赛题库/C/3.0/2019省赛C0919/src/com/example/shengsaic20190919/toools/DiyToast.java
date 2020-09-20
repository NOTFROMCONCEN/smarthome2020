package com.example.shengsaic20190919.toools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsaic20190919.toools
 * @project_name 2019省赛C0919
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {
	// 新建toast
	private static Toast toast;

	/**
	 * DiyToast
	 * 
	 * @param context
	 *            继承Context
	 * @param string
	 *            要显示的String
	 */
	public static void showToast(Context context, String string) {
		// 判断是否为空
		if (toast == null) {
			// 创建
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			// 设置文本
			toast.setText(string);
		}
		// 显示
		toast.show();
	}

}
