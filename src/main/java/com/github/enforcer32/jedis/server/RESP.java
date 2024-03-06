package com.github.enforcer32.jedis.server;

public class RESP {
	public static String nullBulkString() {
		return "$-1\r\n";
	}
}
