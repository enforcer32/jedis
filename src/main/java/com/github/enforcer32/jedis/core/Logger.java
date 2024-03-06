package com.github.enforcer32.jedis.core;

import org.slf4j.LoggerFactory;

public class Logger {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("JedisLogger");

	public static void info(String msg) {
		LOGGER.info(msg);
	}

	public static void warn(String msg) {
		LOGGER.warn(msg);
	}

	public static void error(String msg) {
		LOGGER.error(msg);
	}

	public static void error(String msg, boolean exception) {
		LOGGER.error(msg, new Throwable(msg));
	}

	public static void trace(String msg) {
		LOGGER.trace(msg);
	}

	public static void debug(String msg) {
		LOGGER.debug(msg);
	}
}
