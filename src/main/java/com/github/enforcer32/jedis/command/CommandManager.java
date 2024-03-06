package com.github.enforcer32.jedis.command;

import com.github.enforcer32.jedis.commands.EchoCommand;
import com.github.enforcer32.jedis.commands.GetCommand;
import com.github.enforcer32.jedis.commands.PingCommand;
import com.github.enforcer32.jedis.commands.SetCommand;
import com.github.enforcer32.jedis.server.Encode;
import com.github.enforcer32.jedis.server.Message;
import com.github.enforcer32.jedis.server.Reply;
import com.github.enforcer32.jedis.server.Request;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
	private List<ICommand> commands;

	public CommandManager() {
		commands = new ArrayList<>();

		addCommend(new PingCommand());
		addCommend(new EchoCommand());
		addCommend(new GetCommand());
		addCommend(new SetCommand());
	}

	public CommandManager(List<ICommand> commands) {
		this.commands = commands;
	}

	public void addCommend(ICommand command) {
		if(!hasCommand(command))
			commands.add(command);
	}

	public void handle(Request request, Reply reply) {
		if(request.getCmd() != null) {
			ICommand command = getCommand(request.getCmd().toLowerCase());
			if(command != null) {
				command.execute(new CommandContext(request.getCmd(), request.getArgs(), reply));
			} else {
				reply.reply(Message.unknownCommand(request.getCmd(), request.getArgs()), Encode.ERROR);
			}
		}
	}

	private boolean hasCommand(ICommand command) {
		return getCommand(command.getCommand()) != null;
	}

	private ICommand getCommand(String command) {
		for(ICommand cmd: commands)
			if(cmd.getCommand().equals(command))
				return cmd;
		return null;
	}
}
