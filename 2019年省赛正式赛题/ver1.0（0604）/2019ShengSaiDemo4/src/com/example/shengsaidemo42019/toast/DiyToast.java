package com.example.shengsaidemo42019.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO �Զ���toast
 * @package_name com.example.shengsaidemo42019.toast
 * @project_name 2019ShengSaiDemo4
 * @file_name DiyToast.java
 */
public class DiyToast {
	public static Toast toast;// �½�Toast

	/*
	 * showToast����
	 */
	public static void showToast(Context context, String string)

	{
		if (toast == null) {// ���ToastΪ��
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);// ����Toast��ʾ
		} else {
			toast.setText(string);// �ı���ʾ����
		}
		toast.show();// ��ʾToast
	}
}
