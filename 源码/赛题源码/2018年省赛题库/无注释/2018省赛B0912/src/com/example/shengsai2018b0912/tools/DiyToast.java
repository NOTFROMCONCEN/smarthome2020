package com.example.shengsai2018b0912.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO Toast
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018省赛B0912
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String string) {
		// 如果toast是空（未被创建）
		if (toast == null) {
			// 创建一个toast
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			// 给toast赋值设置文本
			toast.setText(string);
		}
		// 显示toast
		toast.show();
	}

}
