package com.example.shengsaia06062019;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �Զ���Toast
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name DiyToast.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DiyToast {

	private static Toast toast;// �½�Toast

	/**
	 * shouToast����
	 * 
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text) {
		if (toast == null) {// ���toastΪ�գ�δ��������
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);// ����Toast
		} else {// ���toast�Ѿ�����
			toast.setText(text);// �����ı�
		}
		// ��ʾToast
		toast.show();
	}

}
