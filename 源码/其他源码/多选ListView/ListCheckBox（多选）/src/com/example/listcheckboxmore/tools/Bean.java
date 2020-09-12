package com.example.listcheckboxmore.tools;

public class Bean {

	String user, pass, op;

	public Bean(String user, String pass, String op) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.pass = pass;
		this.op = op;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
