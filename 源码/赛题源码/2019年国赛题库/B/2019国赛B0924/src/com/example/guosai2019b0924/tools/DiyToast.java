package com.example.guosai2019b0924.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * �Զ���Toast
 * 
 * @author 10976
 * 
 */
public class DiyToast {
	// �½�toast
	private static Toast toast;

	/**
	 * ���÷���
	 * 
	 * @param context
	 *            �̳�Context
	 * @param string
	 *            Ҫ��ʾ������
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
		// ��ʾtoast
		toast.show();
	}

}
