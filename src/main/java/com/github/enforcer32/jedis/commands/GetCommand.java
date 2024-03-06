package com.github.enforcer32.jedis.commands;

import com.github.enforcer32.jedis.command.CommandContext;
import com.github.enforcer32.jedis.command.ICommand;
import com.github.enforcer32.jedis.core.DatabaseManager;
import com.github.enforcer32.jedis.server.Encode;
import com.github.enforcer32.jedis.server.Message;
import com.github.enforcer32.jedis.server.RESP;

public class GetCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if (ctx.getArgs().size() != 1) {
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
			return;
		}

		String key = ctx.getArgs().get(0);
		String value = DatabaseManager.get(key);
		if(value == null) {
			ctx.getReply().reply(RESP.nullBulkString(), Encode.NONE);
			return;
		}

		ctx.getReply().reply(value, Encode.BULK_STRING);
	}

	@Override
	public String getCommand() {
		return "get";
	}

	@Override
	public String getHelp() {
		return "get key value";
	}
}
