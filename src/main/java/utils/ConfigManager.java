package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import logger.Log;

import java.io.File;
import java.io.IOException;

public final class ConfigManager {

    private final static JsonNode config;
    private final static JsonNode credentials;

    static {
        try {
            config = new ObjectMapper().readTree(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\config.json"));
            credentials = new ObjectMapper().readTree(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException("Config hasn't been loaded");
        }
    }

    private ConfigManager() {}

    public static String getSQLUrl() {
        Log.info("Get SQL URL from config");
        return config.get("sql-db-url").textValue();
    }

    public static String getSQLUser() {
        Log.info("Get SQL user from config");
        return credentials.get("sql-user").textValue();
    }

    public static String getSQLPassword() {
        Log.info("Get SQL password from config");
        return credentials.get("sql-password").textValue();
    }

    public static int getTestsNumber() {
        Log.info("Get tests number from config");
        return config.get("tc2-tests-number").intValue();
    }

    public static String getAuthorName() {
        Log.info("Get author name from config");
        return config.get("author_name").textValue();
    }

    public static String getAuthorLogin() {
        Log.info("Get author login from config");
        return config.get("author_login").textValue();
    }

    public static String getAuthorEmail() {
        Log.info("Get author email from config");
        return config.get("author_email").textValue();
    }
}