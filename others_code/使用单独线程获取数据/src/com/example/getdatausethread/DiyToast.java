package com.example.getdatausethread;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.getdatausethread
 * @project_name ʹ�õ����̻߳�ȡ����
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {
	private static Toast toast;// ����Toast

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
		} else {
			toast.setText(s);
		}
		toast.show();
	}
}
