package gg.grima.economy.player.registry;

import gg.grima.economy.Economy;
import gg.grima.economy.database.PlayerDatabase;
import gg.grima.economy.player.GrimaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerRegistry {

    public Economy plugin;
    public Map<UUID, GrimaPlayer> players;
    public PlayerDatabase database;

    public PlayerRegistry(final Economy plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
        this.database = plugin.getDatabase();
    }

    public void savePlayers() {
        for (final Map.Entry<UUID, GrimaPlayer> player : this.players.entrySet()) {
            this.database.setMoney(player.getKey().toString(), player.getValue().getBalance());
        }
    }

    public void register(final UUID uuid) {
        this.database.setMoney(uuid.toString(), this.database.getMoney(uuid.toString()));
        this.players.put(uuid, new GrimaPlayer(uuid));
    }

    public void register(final UUID uuid, final int amount) {
        this.database.setMoney(uuid.toString(), amount);
        this.players.put(uuid, new GrimaPlayer(uuid, amount));
    }

    public boolean contains(final UUID uuid) {
        return this.players.containsKey(uuid);
    }
}
