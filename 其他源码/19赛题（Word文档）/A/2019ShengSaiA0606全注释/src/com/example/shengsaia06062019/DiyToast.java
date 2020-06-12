package com.example.shengsaia06062019;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 自定义Toast
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name DiyToast.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DiyToast {

	private static Toast toast;// 新建Toast

	/**
	 * shouToast方法
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		if (toast == null) {// 如果toast为空（未被创建）
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);// 创建Toast
		} else {// 如果toast已经存在
			toast.setText(text);// 设置文本
		}
		// 显示Toast
		toast.show();
	}

}
