package com.example.shengsai2018b0912.tools;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 用于存储用户名密码权限状态的java bean
 * @package_name com.example.shengsai2018b0912.tools
 * @project_name 2018省赛B0912
 * @file_name Bean.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class Bean {
	// 用户名、密码、权限
	public String user, pass, op;

	/**
	 * @param user
	 *            用户名
	 * @param pass
	 *            密码
	 * @param op
	 *            权限
	 */
	public Bean(String user, String pass, String op) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.pass = pass;
		this.op = op;
	}

	/**
	 * 获取用户名
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 获取密码
	 * 
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 获取权限
	 * 
	 * @return
	 */
	public String getOp() {
		return op;
	}

	/**
	 * 设置用户名
	 * 
	 * @return
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 设置密码
	 * 
	 * @return
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 设置权限
	 * 
	 * @return
	 */
	public void setOp(String op) {
		this.op = op;
	}

}
