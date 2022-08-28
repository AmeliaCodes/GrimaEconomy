package gg.grima.economy.player;

import lombok.Data;

import java.util.UUID;

@Data
public final class GrimaPlayer {

    private final UUID uuid;
    private int balance;

    public GrimaPlayer(final UUID uuid) {
        this.uuid = uuid;
        this.balance = 0;
    }

    public GrimaPlayer(final UUID uuid, final int balance) {
        this.uuid = uuid;
        this.balance = balance;
    }

    public void addBalance(final int amount) {
        this.balance += amount;
    }

    public void removeBalance(final int amount) {
        this.balance -= amount;
    }

    public void setBalance(final int amount) {
        this.balance = amount;
    }

    public boolean hasEnough(final int required) {
        return this.balance >= balance;
    }
}
