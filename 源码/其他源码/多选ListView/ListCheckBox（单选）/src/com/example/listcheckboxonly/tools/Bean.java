package com.example.listcheckboxonly.tools;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 存储部分信息用的Java Bean
 * @package_name com.example.listcheckboxonly.tools
 * @project_name ListCheckBox（单选）
 * @file_name Bean.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class Bean {
	// 用户名、密码、管理员权限状态
	public String user, pass, op;

	/**
	 * 构造方法
	 * 
	 * @param user
	 * @param pass
	 * @param op
	 */
	public Bean(String user, String pass, String op) {
		// TODO Auto-generated constructor stub
		this.user = user;// 用户名
		this.pass = pass;// 密码
		this.op = op;// 管理员权限
	}

	// 获取用户名
	public String getUser() {
		return user;
	}

	// 获取密码
	public String getPass() {
		return pass;
	}

	// 获取管理员权限
	public String getOp() {
		return op;
	}

	// 设置用户名
	public void setUser(String user) {
		this.user = user;
	}

	// 设置密码
	public void setPass(String pass) {
		this.pass = pass;
	}

	// 设置管理员权限
	public void setOp(String op) {
		this.op = op;
	}

}
