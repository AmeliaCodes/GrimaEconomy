package gg.grima.economy.listeners;

import gg.grima.economy.Economy;
import gg.grima.economy.player.registry.PlayerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class JoinListener implements Listener {

    private final Economy plugin;
    private final PlayerRegistry registry;

    public JoinListener(final Economy plugin) {
        this.plugin = plugin;
        this.registry = plugin.getPlayerRegistry();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (this.registry.contains(player.getUniqueId())) return;

        this.registry.register(player.getUniqueId());
    }
}
