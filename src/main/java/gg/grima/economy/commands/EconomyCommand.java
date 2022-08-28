package gg.grima.economy.commands;

import gg.grima.economy.Economy;
import gg.grima.economy.commands.subcommand.EconomyAddCommand;
import gg.grima.economy.commands.subcommand.EconomyRemoveCommand;
import gg.grima.economy.commands.subcommand.EconomySetCommand;
import gg.grima.economy.player.registry.PlayerRegistry;
import gg.grima.economy.subcommand.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EconomyCommand implements CommandExecutor {

    private final Economy plugin;
    private final FileConfiguration file;
    private final List<SubCommand> subCommands;

    public EconomyCommand(final Economy plugin) {
        this.plugin = plugin;
        this.file = plugin.getConfig();
        this.subCommands = Arrays.asList(
                new EconomyAddCommand(this.plugin),
                new EconomyRemoveCommand(this.plugin),
                new EconomySetCommand(this.plugin)
        );

        plugin.getCommand("economy").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        final Player player = (Player) sender;

        if (!player.hasPermission("grimaeconomy.admin")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.No-Permissions")));
            return false;
        }

        final String arg = args[0];

        for (final SubCommand subCommand : this.subCommands) {
            if (!subCommand.getIdentifier().equalsIgnoreCase(arg)) continue;
            if (!player.hasPermission(subCommand.getPermission())) continue;

            subCommand.execute(
                    sender,
                    command,
                    label,
                    args
            );

            return false;
        }
        return false;
    }
}
