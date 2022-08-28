package gg.grima.economy.commands;

import gg.grima.economy.Economy;
import gg.grima.economy.player.GrimaPlayer;
import gg.grima.economy.player.registry.PlayerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class BalanceCommand implements CommandExecutor {

    private final Economy plugin;
    private final FileConfiguration file;
    private final PlayerRegistry registry;

    public BalanceCommand(final Economy plugin) {
        this.plugin = plugin;
        this.file = plugin.getConfig();
        this.registry = plugin.getPlayerRegistry();

        plugin.getCommand("balance").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        final Player player = (Player) sender;

        if (args.length != 1) {
            final GrimaPlayer profile = this.registry.get(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Current-Balance")).replace("%balance%", NumberFormat.getInstance().format(profile.getBalance())));
            return true;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Invalid-Player")));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        final GrimaPlayer profile = this.registry.get(target.getUniqueId());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Current-Balance-Others")).replace("%player%", target.getName()).replace("%balance%", NumberFormat.getInstance().format(profile.getBalance())));

        return true;
    }
}
