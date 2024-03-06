package com.github.enforcer32.jedis.server;

import com.github.enforcer32.jedis.core.Logger;

import java.util.ArrayList;

public class RequestParser {
	public static Request parse(String data) {
		String cmd = null;
		ArrayList<String> args = new ArrayList<>();

		if(!validate(data))
			Logger.error("RequestParser Redis Syntax Error", true);

		for(int i = 0; i < data.length(); i++) {
			StringBuilder sb = new StringBuilder();

			if(data.charAt(i) == '$') {
				while(data.charAt(i++) != '\n');
				while(data.charAt(i) != '\r')
					sb.append(data.charAt(i++));

				if(cmd != null)
					args.add(sb.toString());
				else
					cmd = sb.toString();
			}
		}

		return new Request(cmd, args);
	}

	private static boolean validate(String data) {
		if(data.charAt(0) != '*')
			return false;
		return true;
	}
}
