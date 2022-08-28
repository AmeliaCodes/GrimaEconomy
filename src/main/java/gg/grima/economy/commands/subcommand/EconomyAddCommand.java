package gg.grima.economy.commands.subcommand;

import gg.grima.economy.Economy;
import gg.grima.economy.player.GrimaPlayer;
import gg.grima.economy.player.registry.PlayerRegistry;
import gg.grima.economy.subcommand.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EconomyAddCommand extends SubCommand {

    private final Economy plugin;
    private final FileConfiguration file;
    private final PlayerRegistry registry;

    public EconomyAddCommand(final Economy plugin) {
        super("add", "grimaeconomy.admin");

        this.plugin = plugin;
        this.file = plugin.getConfig();
        this.registry = plugin.getPlayerRegistry();
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        final Player player = (Player) sender;

        if (args.length != 3) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Invalid-Arguments")));
            return;
        }

        if (Bukkit.getPlayer(args[1]) == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Invalid-Player")));
            return;
        }

        final Player target = Bukkit.getPlayer(args[1]);

        if (Integer.getInteger(args[2]) == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("Messages.Invalid-Number")));
            return;
        }

        final int amount = Integer.getInteger(args[2]);

        final GrimaPlayer targetProfile = this.registry.get(target.getUniqueId());

        targetProfile.addBalance(amount);
    }
}
