package gg.grima.economy.subcommand;

import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Data
public abstract class SubCommand {

    private final String identifier;
    private final String permission;

    public abstract void execute(CommandSender sender, Command command, String label, String[] args);
}
