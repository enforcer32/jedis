package com.github.enforcer32.jedis.server;

import com.github.enforcer32.jedis.command.CommandManager;
import com.github.enforcer32.jedis.core.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class JedisServer {
	public static CommandManager commandManager = new CommandManager();
	private ServerSocket serverSocket;
	private String host;
	private int port;

	public JedisServer(String host, int port) {
		this.host = host;
		this.port = port;

		try {
			serverSocket = new ServerSocket(port, 255, InetAddress.getByName(host));
			serverSocket.setReuseAddress(true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		Logger.info(String.format("Jedis running on '%s':'%d'", host, port));

		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				JedisClientHandler clientHandler = new JedisClientHandler(clientSocket);
				new Thread(clientHandler).start();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static class JedisClientHandler implements Runnable {
		private final Socket clientSocket;

		public JedisClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				String data = SocketUtil.read(clientSocket);
				if(!data.isEmpty()) {
					Request request = RequestParser.parse(data);
					Reply reply = new Reply(clientSocket, new Encoder());
					commandManager.handle(request, reply);
				}

				if(clientSocket != null)
					clientSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
