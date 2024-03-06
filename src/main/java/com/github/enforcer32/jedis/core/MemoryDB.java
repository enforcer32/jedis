package com.github.enforcer32.jedis.core;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryDB implements IDatabase<String, String> {
	private HashMap<String, String> data = new HashMap<>();

	@Override
	public String get(String key) {
		return data.get(key);
	}

	@Override
	public void set(String key, String value) {
		data.put(key, value);
	}

	@Override
	public void set(String key, String value, int expiryMS) {
		set(key, value);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				data.remove(key);
			}
		}, expiryMS);
	}
}
