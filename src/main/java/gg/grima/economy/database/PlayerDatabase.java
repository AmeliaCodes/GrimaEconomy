package gg.grima.economy.database;

import gg.grima.economy.Economy;
import gg.grima.economy.database.connect.ConnectionPool;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDatabase {

    private static String TABLE_NAME = "Accounts";
    private Economy plugin;
    private FileConfiguration file;
    private ConnectionPool connectionPool;
    private Connection connection;

    public PlayerDatabase(final Economy plugin) {
        this.plugin = plugin;
        this.file = plugin.getConfig();

        this.connectionPool = new ConnectionPool(this.plugin);
        this.connection = this.connectionPool.getConnection();
    }

    @SneakyThrows
    public boolean exists(final String UUID) {
        boolean status = false;

        final String queryStatement = "SELECT id FROM " + PlayerDatabase.TABLE_NAME + " WHERE uuid = ?";

        final PreparedStatement statement = this.connection.prepareStatement(queryStatement);

        final ResultSet resultSet = statement.executeQuery();

        statement.setString(1, UUID);

        if (resultSet.next()) {
            status = true;
        }
        resultSet.close();
        statement.close();

        return status;
    }

    @SneakyThrows
    public int getMoney(final String UUID) {

        int money = 0;

        final String queryStatement = "SELECT money FROM " + PlayerDatabase.TABLE_NAME + " WHERE uuid = ?";

        final PreparedStatement statement = this.connection.prepareStatement(queryStatement);

        final ResultSet resultSet = statement.executeQuery();

        statement.setString(1, UUID);

        if (resultSet.next()) {
            money = resultSet.getInt("money");
        }

        resultSet.close();
        statement.close();

        return money;
    }

    @SneakyThrows
    public void setMoney(final String UUID, final int amount) {
        final String queryStatement = "UPDATE " + TABLE_NAME + " SET money = ? WHERE uuid = ?;";

        final PreparedStatement statement = this.connection.prepareStatement(queryStatement);

        final ResultSet resultSet = statement.executeQuery();

        statement.setInt(1, amount);
        statement.setString(2, UUID);

        resultSet.close();
        statement.close();
    }

    @SneakyThrows
    public void addMoney(final String UUID, final int amount) {
        final String queryStatement = "UPDATE " + TABLE_NAME + " SET money = money + ? WHERE uuid = ?;";

        final PreparedStatement statement = this.connection.prepareStatement(queryStatement);

        final ResultSet resultSet = statement.executeQuery();

        statement.setInt(1, amount);
        statement.setString(2, UUID);

        resultSet.close();
        statement.close();
    }

    @SneakyThrows
    public void removeMoney(final String UUID, final int amount) {
        final String queryStatement = "UPDATE " + TABLE_NAME + " SET money = money - ? WHERE uuid = ?;";

        final PreparedStatement statement = this.connection.prepareStatement(queryStatement);

        final ResultSet resultSet = statement.executeQuery();

        statement.setInt(1, amount);
        statement.setString(2, UUID);

        resultSet.close();
        statement.close();
    }
}
