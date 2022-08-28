package gg.grima.economy;

import gg.grima.economy.commands.EconomyCommand;
import gg.grima.economy.database.PlayerDatabase;
import gg.grima.economy.listeners.JoinListener;
import gg.grima.economy.player.registry.PlayerRegistry;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Economy extends JavaPlugin {

    public PlayerDatabase database;
    public PlayerRegistry playerRegistry;

    @Override
    public void onEnable() {
        this.saveConfig();

        this.loadDatabase();
        this.loadRegistry();
        this.loadCommands();
        this.loadListeners();
    }

    @Override
    public void onDisable() {
        this.playerRegistry.savePlayers();
    }

    private void loadDatabase() {
        this.database = new PlayerDatabase(this);
    }
    private void loadRegistry() {
        this.playerRegistry = new PlayerRegistry(this);
    }

    private void loadCommands() {
        new EconomyCommand(this);
    }

    private void loadListeners() {
        new JoinListener(this);
    }
}
