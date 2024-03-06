package com.github.enforcer32.jedis.server;

public interface IEncoder {
	String encode(String data, Encode encode);
}
