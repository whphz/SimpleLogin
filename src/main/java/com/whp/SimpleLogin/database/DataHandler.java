package com.whp.SimpleLogin.database;


import com.whp.SimpleLogin.pojo.Explayer;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import static com.whp.SimpleLogin.database.ConfigConnection.getConnection;
import static com.whp.SimpleLogin.database.ConfigConnection.tableName;
import static com.whp.SimpleLogin.SimpleLogin.instance;

public class DataHandler {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static Statement statement;
    static String result;
    static Explayer ep;

    public static void createDataBase() {
        File file = new File(instance.getDataFolder(), "MCLoginDB.db");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
            statement.executeUpdate("" +
                    "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                    "pid integer PRIMARY KEY," +
                    "name varcahr(20) UNIQUE," +
                    "nickname varchar(30)," +
                    "password varchar(30) NOT NULL," +
                    "pip varchar(50)," +
                    "lastlogin varchar(30)" +
                    ");");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public static void loadingAllPlayer() {
//        try {
//            Statement statement = getConnection().createStatement();
//            ResultSet rs = statement.executeQuery("select * from " + tableName);
//            while (rs.next()) {
//                Explayer ep = new Explayer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
//                ep.setLastlogin(rs.getString(6));
//                getPlayerCache().addPlayer(ep);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static String serachRow(String row, String playerName) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select " + row + " from " + tableName + " where name=?");
            preparedStatement.setString(1, playerName);
            ResultSet rs = preparedStatement.executeQuery();
            result = "";
            if (row.equals("pid")) {
                if (rs.getInt(row) != 0)
                    result = String.valueOf(rs.getInt(row));
            } else {
                if (!rs.getString(row).equals("false"))
                    result = rs.getString(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static synchronized Explayer search(String playerName) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select name,nickname,password,pip,lastlogin from " + tableName + " where name=?");
            preparedStatement.setString(1, playerName);
            ResultSet rs = preparedStatement.executeQuery();
            String name = rs.getString(1);
            String nickname = rs.getString(2);
            String password = rs.getString(3);
            String pip = rs.getString(4);
            String lastlogin = rs.getString(5);
            if (name == null) {
                System.out.println("name为空！");
                ep = null;
            } else {
                ep = new Explayer(name, nickname, password, pip, lastlogin, "false");
            }
        } catch (SQLException e) {
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ep;
    }

    public static synchronized boolean addPlayer(Explayer explayer) {
        try {
            explayer.setLastlogin();
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + "(name,nickname,password,pip,lastlogin) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, explayer.getName());
            preparedStatement.setString(2, explayer.getNickname());
            preparedStatement.setString(3, explayer.getPassword());
            preparedStatement.setString(4, explayer.getPip());
            preparedStatement.setString(5, explayer.getLastlogin());
            if (preparedStatement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static synchronized boolean updatePlayer(String row, String playerName, String value) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("update " + tableName + " set " + row + " = ? where name = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, playerName);
            if (preparedStatement.executeUpdate() != 1)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void close() {

    }


}
