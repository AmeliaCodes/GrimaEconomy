package gg.grima.economy.database.connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.grima.economy.Economy;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;

public class ConnectionPool {

    private final Economy plugin;
    private final FileConfiguration file;
    private static HikariDataSource dataSource;

    public ConnectionPool(final Economy plugin) {
        this.plugin = plugin;
        this.file = plugin.getConfig();

        this.init(
                this.file.getString("Database.Host"),
                this.file.getString("Database.Port"),
                this.file.getString("Database.Database"),
                this.file.getString("Database.Username"),
                this.file.getString("Database.Password")
        );
    }

    private void init(String hostname, String port, String database, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false");
        config.setUsername(username);
        config.setPassword(password);
        config.setPoolName("Economy - Database Query");

        this.dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
