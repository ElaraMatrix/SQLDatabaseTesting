package utils.database;

import logger.Log;
import models.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class TableTest {

    private TableTest() {}

    public static Test get(int id) {
        Log.info("Get test: ID=" + id);
        try (ResultSet set = SQLUtility.getStatement().executeQuery("SELECT * FROM test WHERE id=" + id)) {
            set.next();
            return new Test(set.getInt(1), set.getString(2), set.getInt(3), set.getString(4), set.getInt(5), set.getInt(6),
                            set.getString(7), set.getString(8), set.getString(9), set.getString(10), set.getInt(11));
        } catch (SQLException e) {
            throw new RuntimeException("Test record hasn't been gotten" + e);
        }
    }

    private static int getMaxID() {
        Log.info("Get max test ID");
        try (ResultSet set = SQLUtility.getStatement().executeQuery("SELECT MAX(id) FROM test")) {
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Get max ID error");
        }
    }

    public static boolean add(Test test) {
        Log.info("Add test: " + test);
        try {
            SQLUtility.getStatement().executeUpdate("INSERT INTO test (name, status_id, method_name, project_id, session_id, " +
                    "start_time, end_time, env, browser, author_id) VALUES ('" + test.getName() + "', '" +
                                                                                 test.getStatus_id() + "', '" +
                                                                                 test.getMethod_name() + "', '" +
                                                                                 test.getProject_id() + "', '" +
                                                                                 test.getSession_id() + "', '" +
                                                                                 test.getStart_time() + "', '" +
                                                                                 test.getEnd_time() + "', '" +
                                                                                 test.getEnv() + "', '" +
                                                                                 test.getBrowser() + "', '" +
                                                                                 test.getAuthor_id() + "');");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean update(int id, Test updatedTest) {
        Log.info("Update test (ID=" + id + "): " + updatedTest);
        try {
            SQLUtility.getStatement().executeUpdate("UPDATE test SET name='" + updatedTest.getName() + "', status_id='" +
                                                                               updatedTest.getStatus_id() + "', method_name='" +
                                                                               updatedTest.getMethod_name() + "', project_id='" +
                                                                               updatedTest.getProject_id() + "', session_id='" +
                                                                               updatedTest.getSession_id() + "', start_time='" +
                                                                               updatedTest.getStart_time() + "', end_time='" +
                                                                               updatedTest.getEnd_time() + "', env='" +
                                                                               updatedTest.getEnv() + "', browser='" +
                                                                               updatedTest.getBrowser() + "', author_id='" +
                                                                               updatedTest.getAuthor_id() + "' WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //return copy record
    public static Test copy(Test test) {
        Log.info("Copy test: " + test);
        Test copied = new Test(test);
        add(copied);
        return get(getMaxID());
    }

    public static boolean delete(int id) {
        Log.info("Delete test: ID=" + id);
        try {
            return 1 == SQLUtility.getStatement().executeUpdate("DELETE FROM test WHERE id='" + id + "'");
        } catch (SQLException e) {
            throw new RuntimeException("Delete error: ID=" + id);
        }
    }
}