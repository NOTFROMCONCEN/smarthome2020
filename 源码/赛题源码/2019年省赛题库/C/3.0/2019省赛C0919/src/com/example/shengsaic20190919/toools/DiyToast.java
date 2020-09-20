package com.example.shengsaic20190919.toools;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsaic20190919.toools
 * @project_name 2019ʡ��C0919
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {
	// �½�toast
	private static Toast toast;

	/**
	 * DiyToast
	 * 
	 * @param context
	 *            �̳�Context
	 * @param string
	 *            Ҫ��ʾ��String
	 */
	public static void showToast(Context context, String string) {
		// �ж��Ƿ�Ϊ��
		if (toast == null) {
			// ����
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			// �����ı�
			toast.setText(string);
		}
		// ��ʾ
		toast.show();
	}

}
