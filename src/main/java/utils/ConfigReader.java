package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigReader {
    private static final Properties prop = new Properties();
    private static final Logger logger = Logger.getLogger(ConfigReader.class.getName());
    private static String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    public static String env = System.getProperty("env", "dev");
    static {
        try {
            if (env.equals("dev")) {
                CONFIG_FILE_PATH = "src/test/resources/config.properties";
            } 
            FileInputStream input = new FileInputStream(CONFIG_FILE_PATH);
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getValueFromConfig(String key) {
        String normalizedKey = key.trim().toLowerCase();
        String value = prop.getProperty(normalizedKey);

        switch (normalizedKey) {
            case "browser":
                if (value == null || value.isEmpty()) {
                    logger.warning("'browser' key missing. Defaulting to Chrome.");
                    return "chrome";
                }
                return value.trim();

            case "base.url":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'baseUrl' key is missing in config.properties.");
                }
                return value.trim();

            case "headless":
                if (value == null || value.isEmpty()) {
                    logger.warning("'headless' key missing. Defaulting to false.");
                    return "false";
                }
                return value.trim();

            case "username":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'username' key is missing in config.properties.");
                }
                return value.trim();
            case "password":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'password' key is missing in config.properties.");
                }
                return value.trim();
            case "base.api.url":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'base.api.url' key is missing in config.properties.");
                }
                return value.trim();
            case "amazon.account.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'amazon.account.name' key is missing in config.properties.");
                }
                return value.trim();
            case "full.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'full.name' key is missing in config.properties.");
                }
                return value.trim();
            case "street.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'street.name' key is missing in config.properties.");
                }
                return value.trim();
            case "building.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'building.name' key is missing in config.properties.");
                }
                return value.trim();
            case "city.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'city.name' key is missing in config.properties.");
                }
                return value.trim();
            case "district.name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'district.name' key is missing in config.properties.");
                }
                return value.trim();
            case "mobile.number":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'mobile.number' key is missing in config.properties.");
                }
                return value.trim();
            case "user.endpoint":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'user.endpoint' key is missing in config.properties.");
                }
                return value.trim();
            case "user":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'user' key is missing in config.properties.");
                }
                return value.trim();
            case "job":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'job' key is missing in config.properties.");
                }
                return value.trim();
            case "email":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'email' key is missing in config.properties.");
                }
                return value.trim();
            case "first_name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'first_name' key is missing in config.properties.");
                }
                return value.trim();
            case "last_name":
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("'last_name' key is missing in config.properties.");
                }
                return value.trim();
            default:
                throw new RuntimeException("Unsupported config key: '" + key + "'.");
        }
    }
}