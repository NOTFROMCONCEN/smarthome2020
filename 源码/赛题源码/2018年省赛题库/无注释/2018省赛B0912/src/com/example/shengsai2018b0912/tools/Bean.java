package com.example.shengsai2018b0912.tools;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ���ڴ洢�û�������Ȩ��״̬��java bean
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018ʡ��B0912
 * @file_name Bean.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class Bean {
	// �û��������롢Ȩ��
	public String user, pass, op;

	/**
	 * @param user
	 *            �û���
	 * @param pass
	 *            ����
	 * @param op
	 *            Ȩ��
	 */
	public Bean(String user, String pass, String op) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.pass = pass;
		this.op = op;
	}

	/**
	 * ��ȡ�û���
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * ��ȡȨ��
	 * 
	 * @return
	 */
	public String getOp() {
		return op;
	}

	/**
	 * �����û���
	 * 
	 * @return
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * ����Ȩ��
	 * 
	 * @return
	 */
	public void setOp(String op) {
		this.op = op;
	}

}
