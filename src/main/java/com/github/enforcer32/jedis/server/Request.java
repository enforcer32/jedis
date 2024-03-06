package com.github.enforcer32.jedis.server;

import java.util.ArrayList;

public class Request {
	private String cmd;
	private ArrayList<String> args;

	public Request(String cmd, ArrayList<String> args) {
		this.cmd = cmd;
		this.args = args;
	}

	public String getCmd() {
		return cmd;
	}

	public ArrayList<String> getArgs() {
		return args;
	}
}
