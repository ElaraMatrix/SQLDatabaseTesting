package utils.database;

import logger.Log;
import utils.ConfigManager;

import java.sql.*;

public final class SQLUtility {

    private static Connection connection;
    private static Statement statement;

    private SQLUtility() {}

    public static void openConnection() {
        Log.info("Connection with database: " + ConfigManager.getSQLUrl());
        try {
            connection = DriverManager.getConnection(ConfigManager.getSQLUrl(), ConfigManager.getSQLUser(), ConfigManager.getSQLPassword());
            statement = connection.createStatement();
        } catch (SQLException e) {
            closeConnection();
        }
    }

    public static void closeConnection() {
        Log.info("Connection with database is closed");
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException e) {
            Log.error("Connection closing error");
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}