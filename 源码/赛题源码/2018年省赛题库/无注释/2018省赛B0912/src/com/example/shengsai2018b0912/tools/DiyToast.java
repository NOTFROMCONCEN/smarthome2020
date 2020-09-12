package com.example.shengsai2018b0912.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO Toast
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018ʡ��B0912
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {

	private static Toast toast;

	public static void showToast(Context context, String string) {
		// ���toast�ǿգ�δ��������
		if (toast == null) {
			// ����һ��toast
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			// ��toast��ֵ�����ı�
			toast.setText(string);
		}
		// ��ʾtoast
		toast.show();
	}

}
