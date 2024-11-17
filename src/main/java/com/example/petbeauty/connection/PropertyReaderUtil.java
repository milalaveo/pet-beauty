package com.example.petbeauty.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load application properties", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}