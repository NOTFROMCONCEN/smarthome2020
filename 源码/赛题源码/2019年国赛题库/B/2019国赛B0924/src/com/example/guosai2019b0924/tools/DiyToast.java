package com.example.guosai2019b0924.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * 自定义Toast
 * 
 * @author 10976
 * 
 */
public class DiyToast {
	// 新建toast
	private static Toast toast;

	/**
	 * 调用方法
	 * 
	 * @param context
	 *            继承Context
	 * @param string
	 *            要显示的内容
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
		// 显示toast
		toast.show();
	}

}
