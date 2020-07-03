package com.example.shengsaia06302019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsaia06302019.tools
 * @project_name 2019ʡ��A0630
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {
	// �½�Toast
	private static Toast toast;

	/**
	 * ��ʾToast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showToast(Context context, String string) {
		// �ж�toast�ǲ��ǿ�
		if (toast == null) {
			// �½�Toast
			toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
		} else {
			// ����Toast�ı�
			toast.setText(string);
		}
		// ��ʾToast
		toast.show();
	}

}
