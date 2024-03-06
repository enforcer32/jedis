package com.github.enforcer32.jedis.command;

import com.github.enforcer32.jedis.server.Reply;

import java.util.List;

public class CommandContext {
	private final String cmd;
	private final List<String> args;
	private final Reply reply;

	public CommandContext(String cmd, List<String> args, Reply reply) {
		this.cmd = cmd;
		this.args = args;
		this.reply = reply;
	}

	public String getCmd() {
		return cmd;
	}

	public List<String> getArgs() {
		return args;
	}

	public Reply getReply() {
		return reply;
	}
}
