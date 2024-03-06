package com.github.enforcer32.jedis.server;

import com.github.enforcer32.jedis.core.Logger;

public class Encoder implements IEncoder {
	@Override
	public String encode(String data, Encode encode) {
		switch (encode)	{
			case NONE -> {
				return data;
			}
			case ERROR -> {
				return encodeError(data);
			}
			case SIMPLE_STRING -> {
				return encodeSimpleString(data);
			}
			case BULK_STRING -> {
				return encodeBulkString(data);
			}
			default -> Logger.error("Invalid Encode Type", true);
		};
		return data;
	}

	private String encodeError(String data) {
		return "-" + data + "\r\n";
	}

	private String encodeSimpleString(String data) {
		return "+" + data + "\r\n";
	}

	private String encodeBulkString(String data) {
		return "$" + data.length() + "\r\n" + data + "\r\n";
	}
}
