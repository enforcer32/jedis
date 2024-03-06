package com.github.enforcer32.jedis.commands;

import com.github.enforcer32.jedis.command.CommandContext;
import com.github.enforcer32.jedis.command.ICommand;
import com.github.enforcer32.jedis.core.DatabaseManager;
import com.github.enforcer32.jedis.server.Encode;
import com.github.enforcer32.jedis.server.Message;
import com.github.enforcer32.jedis.server.RESP;

public class SetCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if (ctx.getArgs().size() <= 1) {
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
			return;
		}

		String key = ctx.getArgs().get(0);
		String value = ctx.getArgs().get(1);

		if(ctx.getArgs().size() > 2) {
			String behavior = ctx.getArgs().get(2).toLowerCase();
			try {
				if (behavior.equals("ex")) {
					int timeS = Integer.parseInt(ctx.getArgs().get(3));
					DatabaseManager.set(key, value, (timeS * 1000));
				} else if (behavior.equals("px")) {
					int timeMS = Integer.parseInt(ctx.getArgs().get(3));
					DatabaseManager.set(key, value, timeMS);
				} else if(behavior.equals("nx")) {
					if(DatabaseManager.get(key) == null) {
						DatabaseManager.set(key, value);
					} else {
						ctx.getReply().reply(RESP.nullBulkString(), Encode.NONE);
						return;
					}
				} else if(behavior.equals("xx")) {
					if(DatabaseManager.get(key) != null) {
						DatabaseManager.set(key, value);
					} else {
						ctx.getReply().reply(RESP.nullBulkString(), Encode.NONE);
						return;
					}
				} else if(behavior.equals("get")) {
					String oldValue = DatabaseManager.get(key);
					DatabaseManager.set(key, value);
					if(oldValue != null) {
						ctx.getReply().reply(oldValue, Encode.BULK_STRING);
						return;
					} else {
						ctx.getReply().reply(RESP.nullBulkString(), Encode.NONE);
						return;
					}
				}
			} catch (IndexOutOfBoundsException ex) {
				ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
				return;
			}
		} else {
			DatabaseManager.set(key, value);
		}

		ctx.getReply().reply("OK", Encode.SIMPLE_STRING);
	}

	@Override
	public String getCommand() {
		return "set";
	}

	@Override
	public String getHelp() {
		return "set key value";
	}
}
