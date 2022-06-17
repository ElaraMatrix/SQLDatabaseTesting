package utils.database;

import logger.Log;
import models.Author;
import utils.ConfigManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class TableAuthor {

    private TableAuthor() {}

    public static Author getThisAuthor() {
        Log.info("Get author");
        add(new Author(ConfigManager.getAuthorName(), ConfigManager.getAuthorLogin(), ConfigManager.getAuthorEmail()));
        try (ResultSet set = SQLUtility.getStatement().executeQuery("SELECT * FROM author WHERE login='" + ConfigManager.getAuthorLogin() + "'")) {
            set.next();
            return new Author(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException("Author record hasn't been gotten");
        }
    }

    private static void add(Author author) {
        Log.info("Add author: " + author);
        try {
            SQLUtility.getStatement().executeUpdate("INSERT INTO author (name, login, email) VALUES " +
                    "('" + author.getName() + "', '" + author.getLogin() + "', '" + author.getEmail() + "');");
        } catch (SQLException e) {
            Log.warn("Add author error: " + e);
        }
    }
}