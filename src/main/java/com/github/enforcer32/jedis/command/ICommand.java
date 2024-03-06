package com.github.enforcer32.jedis.command;

public interface ICommand {
	void execute(CommandContext ctx);
	String getCommand();
	String getHelp();
}
