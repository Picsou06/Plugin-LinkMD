package fr.picsou06.LinkDiscordMinecraft.utils.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class SimpleCommand extends Command {

  private final CommandExecutor executor;

  public SimpleCommand(String name, String desc, CommandExecutor executor, String... aliases) {
    super(name, desc, "", Arrays.asList(aliases));

    this.executor = executor;
  }

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
    return executor.onCommand(sender, this, commandLabel, args);
  }

}
