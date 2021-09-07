package com.whp.SimpleLogin.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.whp.SimpleLogin.SimpleLogin.instance;


public class ConfigConnection {
    public static String tableName;
    public static String DataSourceName;
    public static String jdbcUrl;
    public static String DataBaseName;

    public static boolean getDataSource() throws ClassNotFoundException, SQLException {
        tableName = instance.getConfig().getString("dataSource.tableName");
        DataSourceName = instance.getConfig().getString("dataSource.DataSourceName");
        DataBaseName = instance.getConfig().getString("dataSource.DataBaseName");
        jdbcUrl = instance.getConfig().getString("dataSource.jdbcUrl");
        Class.forName("org.sqlite.JDBC");
        return true;
    }

    public static Connection getConnection() {
        try {
            Connection connection=DriverManager.getConnection("jdbc:sqlite:plugins/SimpleLogin/"+DataBaseName+".db");
            if (connection != null)
                return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
