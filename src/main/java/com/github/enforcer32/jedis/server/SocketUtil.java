package com.github.enforcer32.jedis.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketUtil {
	public static String read(Socket socket) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while (input.ready())
				sb.append((char)input.read());
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void write(Socket socket, String data) {
		try {
			socket.getOutputStream().write(data.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
