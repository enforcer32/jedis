package com.github.enforcer32.jedis.server;

import java.util.List;

public class Message {
	public static String unknownCommand(String cmd, List<String> args) {
		return String.format("ERR Unknown or disabled command '%s' with args: '%s'", cmd, String.join(" ", args));
	}

	public static String wrongArgumentCount(String cmd) {
		return String.format("ERR wrong number of arguments for '%s' command", cmd);
	}

	public static String syntaxError() {
		return "ERR syntax error";
	}
}
