package com.example.shengsai07012019.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsai07012019.tools
 * @project_name 2019ʡ��0701
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {
	// �½�Toast
	private static Toast toast;

	/**
	 * ��ʾToast�Ľӿ�
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		// ���toastĿǰû�б���������û����ʾ��
		if (toast == null) {
			// ����һ��Toast
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {// toast�Ѵ��ڣ�������ʾ��
			// ����toast���ı�
			toast.cancel();
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		// ��ʾtoast
		toast.show();
	}
}
