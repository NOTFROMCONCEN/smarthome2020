package com.example.listcheckboxonly.tools;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO �洢������Ϣ�õ�Java Bean
 * @package_name com.example.listcheckboxonly.tools
 * @project_name ListCheckBox����ѡ��
 * @file_name Bean.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class Bean {
	// �û��������롢����ԱȨ��״̬
	public String user, pass, op;

	/**
	 * ���췽��
	 * 
	 * @param user
	 * @param pass
	 * @param op
	 */
	public Bean(String user, String pass, String op) {
		// TODO Auto-generated constructor stub
		this.user = user;// �û���
		this.pass = pass;// ����
		this.op = op;// ����ԱȨ��
	}

	// ��ȡ�û���
	public String getUser() {
		return user;
	}

	// ��ȡ����
	public String getPass() {
		return pass;
	}

	// ��ȡ����ԱȨ��
	public String getOp() {
		return op;
	}

	// �����û���
	public void setUser(String user) {
		this.user = user;
	}

	// ��������
	public void setPass(String pass) {
		this.pass = pass;
	}

	// ���ù���ԱȨ��
	public void setOp(String op) {
		this.op = op;
	}

}
