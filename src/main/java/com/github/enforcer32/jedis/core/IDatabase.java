package com.github.enforcer32.jedis.core;

public interface IDatabase<K,V> {
	V get(K key);
	void set(K key, V value);
	void set(K key, V value, int expiryMS);
}
