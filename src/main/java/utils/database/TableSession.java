package utils.database;

import logger.Log;
import models.Session;
import utils.DateFormatUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class TableSession {

    private TableSession() {}

    public static Session get(Date created_time) {
        Log.info("Get session");
        String dateFormat = DateFormatUtility.getFormatDate(created_time);
        add(new Session(dateFormat, 1));
        try (ResultSet set = SQLUtility.getStatement().executeQuery("SELECT * FROM session WHERE created_time='" + dateFormat + "'")) {
            set.next();
            return new Session(set.getInt(1), set.getString(2), set.getString(3), set.getInt(4));
        } catch (SQLException e) {
            throw new RuntimeException("Session record hasn't been gotten");
        }
    }

    private static void add(Session session) {
        Log.info("Add session: " + session);
        try {
            SQLUtility.getStatement().executeUpdate("INSERT INTO session (session_key, created_time, build_number) VALUES " +
                    "('" + session.getSession_key() + "', '" + session.getCreated_time() + "', " + session.getBuild_number() + ");");
        } catch (SQLException e) {
            Log.warn("Add session error: " + e);
        }
    }
}