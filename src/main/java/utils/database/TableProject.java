package utils.database;

import logger.Log;
import models.Author;
import models.Project;
import utils.ConfigManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class TableProject {

    private TableProject() {}

//    private static int getIDByName(String projectName) {
//        Log.info("Get project ID by name");
//        try (ResultSet resultSet = SQLUtility.getStatement().executeQuery("SELECT id FROM project WHERE name='" + projectName + "'")) {
//            resultSet.next();
//            return resultSet.getInt(1);
//        } catch (SQLException e) {
//            add(projectName);
//            return getIDByName(projectName);
//        }
//    }
//
//    private static void add(String projectName) {
//        //Log.info("Add project: " + project);
//        try {
//            SQLUtility.getStatement().executeUpdate("INSERT INTO project (name) VALUES ('" + projectName + "');");
//        } catch (SQLException e) {
//            Log.error("Add project error");
//        }
//    }

    public static Project getThisProject() {
        Log.info("Get project");
        String thisProjectName = System.getProperty("user.dir").substring(System.getProperty("user.dir").lastIndexOf('\\') + 1);
        add(new Project(thisProjectName));
        try (ResultSet set = SQLUtility.getStatement().executeQuery("SELECT * FROM project WHERE name='" + thisProjectName + "'")) {
            set.next();
            return new Project(set.getInt(1), set.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException("Project record hasn't been gotten");
        }
    }

    private static void add(Project project) {
        Log.info("Add project: " + project);
        try {
            SQLUtility.getStatement().executeUpdate("INSERT INTO project (name) VALUES ('" + project.getName() + "');");
        } catch (SQLException e) {
            Log.warn("Add project error: " + e);
        }
    }
}