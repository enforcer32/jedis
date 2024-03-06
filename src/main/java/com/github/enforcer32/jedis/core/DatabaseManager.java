package com.github.enforcer32.jedis.core;

public class DatabaseManager {
	private static MemoryDB memoryDB = new MemoryDB();

	public static String get(String key) {
		return memoryDB.get(key);
	}

	public static void set(String key, String value) {
		memoryDB.set(key, value);
	}

	public static void set(String key, String value, int expiryMS) {
		memoryDB.set(key, value, expiryMS);
	}
}
