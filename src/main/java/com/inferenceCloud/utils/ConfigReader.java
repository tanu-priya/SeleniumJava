package com.inferenceCloud.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String getProperty(String key) {
           Properties p;

        try {
            String user_dir = System.getProperty("user.dir");
            String file_path = user_dir + "/src/main/resources/.properties";
            FileInputStream fileInputStream = new FileInputStream(file_path);
            p = new Properties();
            p.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return p.getProperty(key);
    }
        
    
}
