package com.example.shengsaidemo42019.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义toast
 * @package_name com.example.shengsaidemo42019.toast
 * @project_name 2019ShengSaiDemo4
 * @file_name DiyToast.java
 */
public class DiyToast {
	public static Toast toast;// 新建Toast

	/*
	 * showToast方法
	 */
	public static void showToast(Context context, String string)

	{
		if (toast == null) {// 如果Toast为空
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);// 创建Toast提示
		} else {
			toast.setText(string);// 改变显示内容
		}
		toast.show();// 显示Toast
	}
}
