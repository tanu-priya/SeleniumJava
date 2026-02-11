package com.inferenceCloud.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String getProperty(String key) {
        Properties properties = new Properties();
        String Value;

        try {
            String env = System.getProperty("env", "dev");
            String user_dir = System.getProperty("user.dir");
            String file_path = user_dir + "/src/main/resources/" + env + ".properties";
            FileInputStream fileInputStream = new FileInputStream(file_path);
            properties.load(fileInputStream);
            System.out.println(">>> ENV loaded: " + env);
            Value = properties.getProperty(key);
            if (Value == null) {
                throw new RuntimeException("❌ Property not found: " + key);
            }
            if (Value.startsWith("${") && Value.endsWith("}")) {
                String envKey = Value.substring(2, Value.length() - 1);
                String envValue = System.getenv(envKey);

                if (envValue == null) {
                    throw new RuntimeException("❌ Environment variable not set: " + envKey);
                }
                return envValue;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Value;
    }

}
