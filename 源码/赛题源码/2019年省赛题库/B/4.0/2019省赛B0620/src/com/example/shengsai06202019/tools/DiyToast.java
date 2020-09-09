package com.example.shengsai06202019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsai06202019.tools
 * @project_name 2019ʡ��0620
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {

	// ����
	private static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast == null) {
			// ����һ��Toast
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		} else {
			// ������ʾ�ı�
			toast.setText(text);
		}
		// ��ʾToast
		toast.show();
	}

}
