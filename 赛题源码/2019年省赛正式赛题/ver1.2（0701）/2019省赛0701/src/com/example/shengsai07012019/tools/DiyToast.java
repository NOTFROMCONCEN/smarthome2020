package com.example.shengsai07012019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsai07012019.tools
 * @project_name 2019省赛0701
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {
	// 新建Toast
	private static Toast toast;

	/**
	 * 显示Toast的接口
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		// 如果toast目前没有被创建（即没有显示）
		if (toast == null) {
			// 创建一个Toast
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {// toast已存在（正在显示）
			// 设置toast的文本
			toast.cancel();
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		// 显示toast
		toast.show();
	}
}
