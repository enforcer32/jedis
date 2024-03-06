package com.github.enforcer32.jedis.server;

import java.net.Socket;

public class Reply {
	private Socket socket;
	private IEncoder encoder;

	public Reply(Socket socket, IEncoder encoder) {
		this.socket = socket;
		this.encoder = encoder;
	}

	public void reply(String data, Encode encode) {
		String encoded = encoder.encode(data, encode);
		SocketUtil.write(socket, encoded);
	}
}
